package com.example.companyms.Company;

import java.util.ArrayList;
import java.util.List;

public class practise {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.stream().map(n -> n*2 ).forEach(n -> System.out.println(" "+n));
    }
}
