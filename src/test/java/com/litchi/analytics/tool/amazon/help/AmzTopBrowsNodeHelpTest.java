package com.litchi.analytics.tool.amazon.help;

import java.util.Map;

import com.litchi.analytics.tool.amazon.model.AmzBrowseNode;
import org.junit.Test;
import static org.junit.Assert.*;


public class AmzTopBrowsNodeHelpTest {
    @Test
    public void testLoadAmazonTopBrowseNodes(){
        Map<String,AmzBrowseNode> dict= AmzTopBrowsNodeHelp.getTopNodeDict();
        assertEquals(35, dict.size());
    }
}
