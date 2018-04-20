package com.jlhc.solr.service.impl;

import com.jlhc.base_com.dao.CustomerMapper;
import com.jlhc.base_com.dto.Company;
import com.jlhc.base_com.dto.Customer;
import com.jlhc.base_com.dto.example.CustomerExample;
import com.jlhc.oa.service.impl.BaseServiceImpl;
import com.jlhc.solr.dto.CompanySolr;
import com.jlhc.solr.service.CompanySolrService;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.params.SolrParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
@Transactional
public class CompanySolrServiceImpl extends BaseServiceImpl implements CompanySolrService {
    @Autowired
    CustomerMapper customerMapper;

    CustomerExample customerExample = new CustomerExample();

    public static SolrServer server;

    static{
        String url = "http://192.168.0.8:8983/solr/jlhc-core";
        HttpSolrServer httpSolrServer = new HttpSolrServer( url );
        httpSolrServer.setSoTimeout(1000);  // socket read timeout
        httpSolrServer.setConnectionTimeout(100);
        httpSolrServer.setDefaultMaxConnectionsPerHost(100);
        httpSolrServer.setMaxTotalConnections(100);
        httpSolrServer.setFollowRedirects(false);  // defaults to false
        // allowCompression defaults to false.
        // Server side must support gzip or deflate for this to have any effect.
        httpSolrServer.setAllowCompression(true);
        httpSolrServer.setMaxRetries(1);
        server = httpSolrServer;
    }

    /**
     * 添加或者修改
     *
     * @param company
     * @throws IOException
     * @throws SolrServerException
     */
    @Override
    public UpdateResponse createOrReworkSolrCompany(Company company) throws IOException, SolrServerException {
        //SolrServer server = getSolrServer();
        CompanySolr companySolr = new CompanySolr();
        companySolr.setComId(company.getComId());
//        companySolr.setComBussinessScope(company.getComBussinessScope());
//        companySolr.setComDescription(company.getComDescription());
        companySolr.setComName(company.getComName());
        companySolr.setComUnicode(company.getComUnicode());

//        customerExample.clear();
//        customerExample.createCriteria()
//                .andCusNameEqualTo(company.getCusName());
//        List<Customer> customers = customerMapper.selectByExample(customerExample);
//        if (null != customers&&0 < customers.size()){
//            return -3;
//        }
        companySolr.setCusName(company.getCusName());
        //查一下其他相关人物,有就添加
        customerExample.clear();
        customerExample.createCriteria()
                .andComIdEqualTo(company.getComId());
//                .andComIdNotEqualTo(company.getCusId());
        List<Customer> customers = customerMapper.selectByExample(customerExample);
        //从集合里面排除一下法定代表人
//        for (int i= 0;i<customers.size();i++){
//            if (customers.contains(customer)){
//                customers.remove(i);
//            };
//        }
        if (0 < customers.size()){
            companySolr.setOtherCusNames(this.convertCollectionToCollectionSolrString(customers));
        }
        UpdateResponse rsp = server.addBean(companySolr);
        server.commit();
        return rsp;
    }

    @Override
    public String convertCustomerToSolrString(Customer customer){
        StringBuilder customerString = new StringBuilder();
        customerString.append("{");
        customerString.append(customer.getCusName());
        customerString.append(":");
        customerString.append(customer.getCallMain());
        String callSecondary = customer.getCallSecondary();
        if ((null != callSecondary)&&(!callSecondary.isEmpty())){
            customerString.append(";");
            customerString.append(callSecondary);
        }
        customerString.append("}");
        return customerString.toString();
    }

    @Override
    public SolrDocumentList queryAllCompanyFromSolr() throws SolrServerException {
        SolrQuery solrQuery = new SolrQuery();
        solrQuery.set("q","*:*");
        SolrServer solrServer = getSolrServer();
        QueryResponse response = solrServer.query(solrQuery);
        SolrDocumentList results = response.getResults();
        return results;
    }

    /**
     * 根据联系人姓名或者电话模糊查询任务
     *
     * @param nameOrMobile
     * @return
     * @throws SolrServerException
     */
    @Override
    public List<CompanySolr> querySolrCompanyByNameOrMobile(String nameOrMobile)throws SolrServerException {
        SolrQuery solrQuery = new SolrQuery();
        StringBuilder cusName = new StringBuilder();
        cusName.append("cusName:*");
        cusName.append(nameOrMobile);
        cusName.append("*");
        String s = cusName.toString();
        solrQuery.set("q",s);
        SolrServer solrServer = this.getSolrServer();
        QueryResponse response = solrServer.query(solrQuery);
        SolrDocumentList results = response.getResults();
        System.out.println(results);
        return null;
    }

    /**
     * solr查询接口,查询接口都会将已经注销的企业不显示
     * 根据社会统一信用代码精确查
     * 根据企业信息名称模糊查
     * 根据法人真实姓名模糊查
     * 根据其他人真实姓名模糊查
     * 根据电话号查
     * "comId":"b6fe7986fe2245a88fa5baff3f0c50bb",
     *         "comUnicode":["茶点铺子的社会统一信息代码"],
     *         "comName":["三大炮茶点铺子"],
     *         "cusName":"C.罗纳尔多:{13585759965;66663@163.com}",
     *         "comBussinessScope":"吃三大炮",
     *         "comDescription":"小吃特别的甜蜜呀",
     *         "_version_":1595081054201315328},
     *
     * @param words
     * @return
     */
    @Override
    public List<CompanySolr> querySolrCompanyByFuzzySearchAll(String words) throws SolrServerException {
//        System.out.println(words);
        SolrQuery solrQuery = new SolrQuery();
        String sq = "comUnicode:*" + words + "* or comName:*" + words + "* or cusName:*"
                    + words +"* OR otherCusNames:*" + words + "*";
//        System.out.println(sq);
        solrQuery.set("q",sq);
        QueryResponse rsp = server.query(solrQuery);
        SolrDocumentList docs = rsp.getResults();
        List<CompanySolr> beans = rsp.getBeans(CompanySolr.class);
        return beans;
    }


    /**
     * 判断customer是否在集合中
     * 存入solr中的customer数据判断是不是重复,仅仅用于存入solrCompany中的数据尽量不要出现重复
     * 如果姓名相同,而且第一联系方式电话存在于callMain,callSecondary中,就说明是同一个用户,返回true
     *
     * @param customer
     * @param customers
     * @return
     */
    @Override
    public boolean hasTheSameCus(Customer customer , List<Customer> customers){
        boolean result = false;
        if (null == customer.getCusName()||customer.getCusName().isEmpty()){
            return true;
        }
        for (Customer customerAtList :customers ) {
            boolean hasTheSameName = customer.getCusName().equalsIgnoreCase(customerAtList.getCusName());
            //名字有相同的,但是CallMain是空的,说明重复
            if (hasTheSameName&&(null == customer.getCallMain()||customer.getCallMain().isEmpty())){
                return true;
            }
            //如果被检索的第二联系方式是空的,只需要判断CallMain
            if ((null == customerAtList.getCallSecondary()||customerAtList.getCallSecondary().isEmpty())){
                return hasTheSameName&&(customer.getCallMain().equalsIgnoreCase(customerAtList.getCallMain()));
            }else{
                //
                boolean callMainIn = (customer.getCallMain().equalsIgnoreCase(customerAtList.getCallMain()))
                        ||(customerAtList.getCallSecondary().indexOf(customer.getCallMain())==-1);
                if (hasTheSameName&&callMainIn){
                    return true;
                }
            }
        }
        return result;
    }

    /**
     * 去重与转换
     *
     * @param customers
     * @return
     */
    @Override
    public String convertCollectionToCollectionSolrString(List<Customer> customers){
        //去一遍重
        List<Customer> newList = new  ArrayList<>();

//        for (int i = 0;i < customers.size();i++){
//            if (!this.hasTheSameCus(customers.get(i),newList)){
//                newList.add(customers.get(i));
//            }
//        }
        for (Customer c :customers ) {
            if(!this.hasTheSameCus(c,newList)){
                newList.add(c);
            }
        }
        //转换
        ArrayList<String> customersResultString = new ArrayList<>();
        for (Customer customer :newList ) {
            String cusResultString = this.convertCustomerToSolrString(customer);
            customersResultString.add(cusResultString);
        }

//格式转换没有意义
//        StringBuilder stringBuilder = new StringBuilder();
//        stringBuilder.append("[");
//        int i = 0;
//        for (String solrString : newList) {
//            i++;
//            stringBuilder.append(solrString);
//            if(newList.size()!=i) {
//                stringBuilder.append(";"+" ");
//            }else{
//                stringBuilder.append("]");
//            }
//        }
        return customersResultString.toString();
    }

    /**
     * 根据名称模糊查询
     *
     * @param comName
     * @return
     * @throws SolrServerException
     */
    @Override
    public List<CompanySolr> querySolrCompanyByName(String comName) throws SolrServerException {
        SolrQuery solrQuery = new SolrQuery();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("comName:*");
        stringBuilder.append(comName);
        stringBuilder.append("*");
        String s = stringBuilder.toString();
        solrQuery.set("q",s);
        QueryResponse rsp = server.query(solrQuery);
        SolrDocumentList docs = rsp.getResults();
        List<CompanySolr> beans = rsp.getBeans(CompanySolr.class);
        return beans;
    }

    /**
     * 删除solr中的某一条数据
     *
     * @param comId
     * @throws IOException
     * @throws SolrServerException
     */
    @Override
    public void dropCompanyByComId(String comId) throws IOException, SolrServerException {
        SolrServer solrServer = getSolrServer();
        solrServer.deleteById(comId);
        solrServer.commit();
    }




    //不用
    @Override
    public SolrServer getSolrServer() {
        String url = "http://192.168.0.8:8983/solr/jlhc-core";
        HttpSolrServer server = new HttpSolrServer( url );
        server.setSoTimeout(1000);  // socket read timeout
        server.setConnectionTimeout(100);
        server.setDefaultMaxConnectionsPerHost(100);
        server.setMaxTotalConnections(100);
        server.setFollowRedirects(false);  // defaults to false
        // allowCompression defaults to false.
        // Server side must support gzip or deflate for this to have any effect.
        server.setAllowCompression(true);
        server.setMaxRetries(1); // defaults to 0.  > 1 not recommended.
        return server;
    }



    //不用
    @Override
    public void reworkByComId(Company company) throws IOException, SolrServerException {
        SolrServer solrServer = getSolrServer();
        CompanySolr companySolr = new CompanySolr();
        customerExample.clear();
        customerExample.createCriteria()
            .andComIdEqualTo(company.getCusName());
        List<Customer> customers = customerMapper.selectByExample(customerExample);
        if (customers.size() > 0){
            companySolr.setOtherCusNames(customers.toString());
        }
//        Customer customer = customerMapper.selectByPrimaryKey(company.getCusId());
        companySolr.setCusName(company.getCusName());
        companySolr.setComUnicode(company.getComUnicode());
        companySolr.setComName(company.getComName());
//        companySolr.setComDescription(company.getComDescription());
//        companySolr.setComBussinessScope(company.getComBussinessScope());
        solrServer.addBean(companySolr);
    }
}
