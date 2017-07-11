package com.lychee.analytics.tool.utl;


import com.lychee.analytics.tool.amazon.entity.AmzKeywordEntity;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ListUtlTest {
    private List<String> testList = Arrays.asList("a", "b", "c");

    @Before
    public void setup(){
    }


    @Test
    public void setClassName(){
        Class<?> clazz = AmzKeywordEntity.class;
        String name = clazz.getSimpleName();
        assertEquals("AmzKeywordEntity", name);
    }

    @Test
    public void testLeftIsEmpty(){
        List<String> part1 = new ArrayList<>();
        List<String> part2 = new ArrayList<>();


        ListUtl.splitListByMidIndex(testList, -1, part1, part2);
        assertEquals(0, part1.size());
        assertEquals(3, part2.size());
    }

    @Test
    public void testSplitInTheMid(){
        List<String> part1 = new ArrayList<>();
        List<String> part2 = new ArrayList<>();


        ListUtl.splitListByMidIndex(testList, 1, part1, part2);
        assertEquals(2, part1.size());
        assertEquals(1, part2.size());
    }


    @Test
    public void testRightIsEmpty(){
        List<String> part1 = new ArrayList<>();
        List<String> part2 = new ArrayList<>();


        ListUtl.splitListByMidIndex(testList, 2, part1, part2);
        assertEquals(3, part1.size());
        assertEquals(0, part2.size());
    }
}
