package com.litchi.analytics.tool.amazon.model;


import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AmzProductPage {
    private BigInteger count;
    private List<AmzProductSummary> products = new ArrayList<AmzProductSummary>();


    public BigInteger getCount() {
        return count;
    }


    public void setCount(BigInteger count) {
        this.count = count;
    }


    public List<AmzProductSummary> getProducts() {
        return products;
    }


    public void setProducts(List<AmzProductSummary> products) {
        this.products = products == null? Collections.<AmzProductSummary>emptyList(): products;
    }
}
