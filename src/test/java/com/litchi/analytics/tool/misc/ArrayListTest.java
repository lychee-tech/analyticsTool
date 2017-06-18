package com.litchi.analytics.tool.misc;

import com.litchi.analytics.tool.amazon.entity.AmzKeywordEntity;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ArrayListTest {
    @Test
    public void testContainObject() {
        List<AmzKeywordEntity> keywords = new ArrayList<AmzKeywordEntity>();
        keywords.add(new AmzKeywordEntity().setWord("a").setSearch("search1"));
        keywords.add(new AmzKeywordEntity().setWord("b").setSearch("search2"));

        assertTrue(keywords.contains(new AmzKeywordEntity().setWord("a")));
        assertTrue(keywords.contains(new AmzKeywordEntity().setWord("b")));
        assertFalse(keywords.contains(new AmzKeywordEntity().setWord("search1")));
    }
}
