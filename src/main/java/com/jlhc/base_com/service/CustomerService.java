package com.jlhc.base_com.service;

import com.jlhc.base_com.dto.Customer;
import com.jlhc.base_com.dto.CustomerNotValidId;
import org.apache.solr.client.solrj.SolrServerException;

import java.io.IOException;
import java.util.List;

public interface CustomerService {
    Integer createCustomer(CustomerNotValidId customerNotValidId) throws IOException, SolrServerException;

    boolean hasTheSameIdCard(Customer customer, Customer customer1);

    Customer queryCustomersByIdCard(String cusIdCard);

    Integer reworkCustomer(Customer customer) throws IOException, SolrServerException;

    Integer dropCustomerById(String cusId) throws IOException, SolrServerException;

    List<Customer> queryCustomersByComId(String comId);

    String queryRepresentativeOfCom(String comId);

    List<Customer> queryCustomersByName(String cusName);
}
