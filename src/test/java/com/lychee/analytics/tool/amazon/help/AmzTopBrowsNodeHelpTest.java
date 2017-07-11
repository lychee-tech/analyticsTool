package com.lychee.analytics.tool.amazon.help;

import java.util.Map;

import com.lychee.analytics.tool.amazon.model.AmzBrowseNode;
import org.junit.Test;
import static org.junit.Assert.*;


public class AmzTopBrowsNodeHelpTest {
    @Test
    public void testLoadAmazonTopBrowseNodes(){
        Map<String,AmzBrowseNode> dict= AmzTopBrowsNodeHelp.getTopNodeDict();
        assertEquals(35, dict.size());
    }
}
