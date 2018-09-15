package com.example.springcloud_service_zuul.test;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Author: fengzw
 * @Description: todo
 * @Date 2018/6/15上午10:22
 */
public class LRULinkedHashMap extends LinkedHashMap {


    private final int maxCapacity;

    private final static float defacultFactor = 0.75f;

    public LRULinkedHashMap(int maxCapacity) {
        super(maxCapacity, defacultFactor, true);
        this.maxCapacity = maxCapacity;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry eldest) {


        boolean flag = size() > maxCapacity;
        if(flag){
            System.out.println("----delete value:" + eldest.getValue());

        }
        return flag;
    }


    public static void main(String[] args) {

        LRULinkedHashMap lruLinkedHashMap = new LRULinkedHashMap(5);

        for (int i = 1; i <= 5; i++) {
            lruLinkedHashMap.put("" + i, i);
        }
        System.out.println(lruLinkedHashMap.keySet());

        lruLinkedHashMap.get("3");

        System.out.println(lruLinkedHashMap.keySet());

        lruLinkedHashMap.get("1");

        System.out.println(lruLinkedHashMap.keySet());

        lruLinkedHashMap.put("6", 6);

        System.out.println(lruLinkedHashMap.keySet());

    }
}
