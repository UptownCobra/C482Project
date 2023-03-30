package com.example.c482project;
//TODO create documentation for Outsourced class

/**
 * Adds Company name to the Part Class
 * @author Caleb
 */
public class Outsourced extends Part {
    String companyName;
    public Outsourced(int id, String name, double price, int stock, int min, int max, String companyName) {
        super(id, name, price, stock, min, max);
        this.companyName = companyName;
    }

    /**
     * Sets the company name
     * @param companyName String value of Company name
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    /**
     * Returns the Company name
     * @return companyName String value of parts company name
     */
    public String getCompanyName() {
        return companyName;
    }
}
