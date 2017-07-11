package com.lychee.analytics.tool.amazon.help;


import org.apache.commons.lang.StringUtils;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class AutoCompleteHelpTest {
    @Test
    public void testSimpleAutoCompleteReturnAtMost10Matches() throws Exception {
        List<String> result = AutoCompleteHelp.getAutoCompleteMatches("a\"");
        assertNotNull(result);
        assertTrue(result.size()<=10);
    }


    @Test
    public void testEmptyAutoCompleteResult() throws Exception {
        String keyword = StringUtils.repeat("a", 1000);
        List<String> result = AutoCompleteHelp.getAutoCompleteMatches(keyword);
        assertNotNull(result);
        assertEquals(0, result.size());
    }
}
