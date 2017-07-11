package com.lychee.analytics.tool.misc;

import com.lychee.analytics.tool.amazon.entity.AmzCategoryEntity;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;
import static org.junit.Assert.*;

public class SetTest {
    @Test
    public void testSetHasNoDuplicate(){
        Set<AmzCategoryEntity> categories = new HashSet<>();
        categories.add(new AmzCategoryEntity().setBrowseId("b1"));
        categories.add(new AmzCategoryEntity().setBrowseId("b1"));
        categories.add(new AmzCategoryEntity().setBrowseId("b2"));

        assertEquals(2, categories.size());
    }
}
