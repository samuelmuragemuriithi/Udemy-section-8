package com.awesam;

import java.util.ArrayList;

public class Bank {
    private String name;
    private ArrayList<Branch>branches;

    public Bank(String name) {
        this.name = name;
        this.branches = new ArrayList<Branch>();
    }
    public boolean addBranch(String branchName){
        if (findBranch(branchName)==null){
            branches.add(new Branch(branchName));
            return true;
        }
        return false;
    }
    public boolean addCustomer(String branchName,String customerName, double initialAmount){
        Branch branch = findBranch(branchName);
        if (branch!=null){
            return branch.newCustomer(customerName,initialAmount);
        }
        return false;
    }
    public boolean addCustomerTransaction(String branchName,String customerName, double initialAmount){
        Branch branch = findBranch(branchName);
        if (branch!=null){
            return branch.addCustomerTransaction(customerName,initialAmount);
        }
        return false;
    }
    public Branch findBranch(String branchName){
        for (int i = 0; i < branches.size(); i++) {
            Branch checkedBranch = this.branches.get(i);
            if (checkedBranch.getName().equals(branchName)){
                return checkedBranch;
            }
        }
        return null;

    }
    public boolean listCustomers(String branchName, boolean showTransaction) {
        Branch branch = findBranch(branchName);
        if (branch != null) {
            System.out.println("Customer details for branch " + branch.getName());

            ArrayList<Customer> branchCustomers = branch.getCustomers();
            for (int i = 0; i < branchCustomers.size(); i++) {
                Customer branchCustomer = branchCustomers.get(i);
                int cutomerIndex =i + 1 ;
                System.out.println("Customer: " + branchCustomer.getName() + "[ " + cutomerIndex+ " ]");
                if (showTransaction) {
                    System.out.println("Transactions");
                    ArrayList<Double> transaction = branchCustomer.getTransactions();
                    for (int j = 0; j < transaction.size(); j++) {
                        System.out.println("[ " +( j + 1)+" ] Amount " + transaction.get(j));

                    }
                }
            } return true;
        } else return false;
    }
}
