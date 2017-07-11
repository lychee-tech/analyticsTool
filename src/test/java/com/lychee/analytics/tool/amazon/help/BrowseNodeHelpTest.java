package com.lychee.analytics.tool.amazon.help;


import com.lychee.analytics.tool.amazon.model.AmzBrowseNode;
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

    @Test
    public void testTwoBrowseNodeWithTheSameName(){
        List<AmzBrowseNode> children = new ArrayList<>();
        AmzBrowseNode node = BrowseNodeHelp.getBrowseNode("15720661", children);
        children.clear();
        node = BrowseNodeHelp.getBrowseNode("15721251", children);
    }
}
