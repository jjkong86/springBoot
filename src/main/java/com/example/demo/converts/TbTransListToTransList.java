package com.example.demo.converts;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.objects.TbTrans;
import com.example.demo.objects.Transaction;


public class TbTransListToTransList {

    public static List<Transaction> convert(List<TbTrans> items) {
        List<Transaction> ret = new ArrayList<>();
        for (TbTrans item : items) {
            ret.add(new Transaction(item));
        }
        return ret;
    }

}
