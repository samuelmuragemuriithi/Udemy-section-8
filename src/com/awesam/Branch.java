package com.awesam;

import java.util.ArrayList;

public class Branch {
    private String name;
    private ArrayList<Customer> customers;

    public Branch(String name) {
        this.name = name;
        this.customers = new ArrayList<Customer>();
    }

    public String getName() {
        return name;
    }

    public ArrayList<Customer> getCustomers() {
        return customers;
    }

    public boolean newCustomer(String customersName, double initialAmount) {
        if (findCustomer(customersName) == null) {
            this.customers.add(new Customer(customersName, initialAmount));
            return true;
        }
        return false;
    }

    public boolean addCustomerTransaction(String customersName, double amount ){
        Customer existingCustomer = findCustomer(customersName);
        if (existingCustomer != null){
            existingCustomer.addTransaction(amount);
            return true;
        }

        return false;

    }
    public Customer findCustomer(String customersName){
        for (int i = 0; i < customers.size(); i++) {
           Customer checkedCustomer = this.customers.get(i);
           if (checkedCustomer.getName().equals(customersName)){
               return checkedCustomer;
           }
        }
        return null;

    }
}
