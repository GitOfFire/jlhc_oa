package com.jlhc.solr.service;

import com.jlhc.base_com.dto.Company;
import com.jlhc.base_com.dto.Customer;
import com.jlhc.solr.dto.CompanySolr;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrDocumentList;

import java.io.IOException;
import java.util.List;

public interface CompanySolrService {

    UpdateResponse createOrReworkSolrCompany(Company company) throws IOException, SolrServerException;

    List<CompanySolr> querySolrCompanyByName(String comName) throws SolrServerException;

    SolrServer getSolrServer();

    void dropCompanyByComId(String comId) throws IOException, SolrServerException;

    void reworkByComId(Company finalCompany) throws IOException, SolrServerException;

    boolean hasTheSameCus(Customer customer , List<Customer> customers);

    String convertCollectionToCollectionSolrString(List<Customer> customers);

    String convertCustomerToSolrString(Customer customer);

    SolrDocumentList queryAllCompanyFromSolr() throws SolrServerException;

    List<CompanySolr> querySolrCompanyByNameOrMobile(String nameOrMobile) throws SolrServerException;

    List<CompanySolr> querySolrCompanyByFuzzySearchAll(String words) throws SolrServerException;
}
