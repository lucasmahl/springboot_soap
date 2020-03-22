package com.lucasmahl.soap.webservice.customersadministration.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.lucasmahl.soap.webservice.customersadministration.bean.Customer;
import com.lucasmahl.soap.webservice.customersadministration.helper.Status;

@Component
public class CustomerDetailService {

	private static List<Customer> customers = new ArrayList<>();

	static {
		Customer customer1 = new Customer(1, "Bob", "99999999", "bob@gmail.com");
		Customer customer2 = new Customer(2, "Mark", "88888888", "mark@gmail.com");
		Customer customer3 = new Customer(3, "Jose", "77777777", "jose@gmail.com");
		Customer customer4 = new Customer(4, "Lucas", "66666666", "lucas@gmail.com");

		customers.addAll(Arrays.asList(customer1, customer2, customer3, customer4));
	}

	// retorna cliente pelo id informado
	public Customer findById(int id) {

		Optional<Customer> customerOptional = customers.stream().filter(c -> c.getId() == id).findAny();

		if (customerOptional.isPresent()) {
			return customerOptional.get();
		}

		return null;
	}

	// retorna lista toda
	public List<Customer> findAll() {
		return customers;
	}

	// deleta cliente pelo id informado
	public Status deleteById(int id) {
		Optional<Customer> customerOptional = customers.stream().filter(c -> c.getId() == id).findAny();

		if (customerOptional.isPresent()) {
			customers.remove(customerOptional.get());

			return Status.SUCCESS;
		}

		return Status.FAILURE;
	}

}
