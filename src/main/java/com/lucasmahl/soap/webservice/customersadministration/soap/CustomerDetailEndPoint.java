package com.lucasmahl.soap.webservice.customersadministration.soap;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.lucasmahl.soap.webservice.customersadministration.bean.Customer;
import com.lucasmahl.soap.webservice.customersadministration.service.CustomerDetailService;

import br.com.lucasmahl.CustomerDetail;
import br.com.lucasmahl.GetCustomerDetailRequest;
import br.com.lucasmahl.GetCustomerDetailResponse;

@Endpoint
public class CustomerDetailEndPoint {

	@Autowired
	CustomerDetailService service;

	@PayloadRoot(namespace = "http://lucasmahl.com.br", localPart = "GetCustomerDetailRequest")
	@ResponsePayload
	public GetCustomerDetailResponse processCustomerDetailRequest(@RequestPayload GetCustomerDetailRequest req)
			throws Exception {
		Customer customer = service.findById(req.getId());

		if (customer == null) {
			throw new Exception("Ivalid Customer id" + req.getId());
		}

		return convertToGetCustomerDetailResponse(customer);
	}

	private GetCustomerDetailResponse convertToGetCustomerDetailResponse(Customer customer) {
		GetCustomerDetailResponse resp = new GetCustomerDetailResponse();

		resp.setCustomerDetail(convertToCustomerDetail(customer));

		return resp;
	}

	// converte pro CustomerDetail
	private CustomerDetail convertToCustomerDetail(Customer customer) {
		CustomerDetail customerDetail = new CustomerDetail();

		customerDetail.setId(customer.getId());
		customerDetail.setName(customer.getName());
		customerDetail.setPhone(customer.getPhone());
		customerDetail.setEmail(customer.getEmail());

		return customerDetail;
	}
	
	//converte pra getallcustomerdetailreponse ERRO
	/*private GetAllCustomerDetailResponse convertToGetAllCustomerDetailResponse(List<Customer> customers) {
		GetAllCustomerDetailResponse resp = new GetAllCustomerDetailResponse();
		
		customers.stream().forEach(c->resp.getCustomerDetail().add(c));
		
	}*/

}