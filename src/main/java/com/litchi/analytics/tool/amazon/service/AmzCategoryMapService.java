package com.litchi.analytics.tool.amazon.service;

import com.litchi.analytics.tool.amazon.help.AmzTopBrowsNodeHelp;
import com.litchi.analytics.tool.amazon.model.AmzBrowseNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * this service is used to build amazon category tree
 *   it may not be a tree. a child category may have multiple parent categories.
 *   the list of top level category can be found in http://www.browsenodes.com/node--2000.html
 *
 *
 */
@Service
public class AmzCategoryMapService {
    @Autowired
    private AmzCategoryService categoryService;
    private Map<String,AmzBrowseNode> topNodes = new HashMap<>();



    public AmzCategoryMapService(){
        this.topNodes = AmzTopBrowsNodeHelp.getTopNodeDict();
    }

    /**
     * setup top nodes, for testing purpose
     *
     * @param topNodes
     */
    public void setTopNodes(Map<String, AmzBrowseNode> topNodes) {
        this.topNodes = topNodes;
    }



    private void buildCategoryRelationMap(){
        topNodes.forEach((k,v)->{
            String browseId = k;
            AmzBrowseNode node = v;
            doBuildRelationship(node);
        });
    }

    public void doBuildRelationship(AmzBrowseNode node) {
        List<AmzBrowseNode> children = categoryService.saveCategoryAndChildren(node.getBrowseId());
        children.forEach(c->doBuildRelationship(c));
    }

}
