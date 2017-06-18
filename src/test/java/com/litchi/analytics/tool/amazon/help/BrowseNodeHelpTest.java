package com.litchi.analytics.tool.amazon.help;


import com.litchi.analytics.tool.amazon.model.AmzBrowseNode;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;

public class BrowseNodeHelpTest {
    @Test
    public void testRetrieveOneBrowseNode(){
        List<AmzBrowseNode> children = new ArrayList<>();

        AmzBrowseNode node = BrowseNodeHelp.getBrowseNode("172282", children);
        assertNotNull(node);
        assertEquals("172282", node.getBrowseId());
        assertTrue(children.size()>0);
    }
}
