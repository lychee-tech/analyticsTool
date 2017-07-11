package com.lychee.analytics.tool.misc;


import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class StringTest {

    @Test
    public void testStringIsPassedByValue(){
        String value = "hello";
        changeStringValue(value);
        assertEquals("hello", value);
    }


    @Test
    public void charComparison(){
        byte c1=' ';
        byte c2='a';
        assertTrue(c1 < c2);
    }

    @Test
    public void ListContains(){
        List<String> list = new ArrayList<String>();
        String tmp ="bc";
        list.add("ab");
        list.add("bc");
        list.add("ef");
        String d="abc".substring(1);
        assertTrue(list.contains(d));
    }



    private void changeStringValue(String value) {
        value = value +"-changed";
    }
}
