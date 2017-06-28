package com.litchi.analytics.tool.amazon.service;

import com.litchi.analytics.tool.amazon.help.AmzTopBrowsNodeHelp;
import com.litchi.analytics.tool.amazon.model.AmzBrowseNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * this service is used to build amazon category tree
 *   it may not be a tree. a child category may have multiple parent categories.
 *   the list of top level category can be found in http://www.browsenodes.com/node--2000.html
 *
 *   https://www.amazon.com/b/ref=sr_aj?node=133140011&ajr=0
 */
@Service
public class AmzCategoryMapService {
    private static Logger logger = LoggerFactory.getLogger(AmzCategoryMapService.class);
    @Autowired
    private AmzCategoryService categoryService;

    private Map<String,AmzBrowseNode> topNodes = new HashMap<>();
    private Set<String> duplicateSet = new HashSet<>();



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



    public void buildCategoryRelationMap(){
        logger.debug("buildCategoryRelationMap started");

        topNodes.forEach((k,v)->{
            AmzBrowseNode node = v;
            doBuildRelationship(node);
        });
    }

    private void doBuildRelationship(AmzBrowseNode node) {
        if (duplicateSet.contains(node.getBrowseId())) {
            return;
        }
        duplicateSet.add(node.getBrowseId());


        List<AmzBrowseNode> children = categoryService.saveCategoryAndChildren(node.getBrowseId());
        children.forEach(c->doBuildRelationship(c));
    }

}
