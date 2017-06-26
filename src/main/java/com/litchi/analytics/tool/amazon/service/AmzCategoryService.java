package com.litchi.analytics.tool.amazon.service;

import com.litchi.analytics.tool.amazon.entity.AmzCategoryEntity;
import com.litchi.analytics.tool.amazon.help.BrowseNodeHelp;
import com.litchi.analytics.tool.amazon.model.AmzBrowseNode;
import com.litchi.analytics.tool.amazon.repo.AmzCategoryRepo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * this class is used to recursively search for amazon categories and their children
 */

@Service
public class AmzCategoryService {
    private  static Logger logger = LogManager.getLogger(AmzCategoryService.class);
    @Autowired
    AmzCategoryRepo categoryRepo;


    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public List<AmzBrowseNode> saveCategoryAndChildren(String browseNodeId){
        List<AmzBrowseNode> children = new ArrayList<>();
        sleepInSeconds(10);

        //this line will populate children
        AmzBrowseNode node = BrowseNodeHelp.getBrowseNode(browseNodeId, children);

        if (node == null) {
            logger.error(String.format("amazon return null for browse id %s", browseNodeId));
        } else {
            saveCategoryAndChildrenRelationShip(node, children);
        }
        return children;
    }

    private void saveCategoryAndChildrenRelationShip(AmzBrowseNode parentNode, List<AmzBrowseNode> childNodes) {
        AmzCategoryEntity parent = categoryRepo.findOne(parentNode.getBrowseId());
        if (parent == null) {
            parent = new AmzCategoryEntity().setBrowseId(parentNode.getBrowseId()).setName(parentNode.getName());
        }


        for(AmzBrowseNode cNode: childNodes) {
            AmzCategoryEntity c = categoryRepo.findOne(cNode.getBrowseId());
            if (c==null) {
                c = new AmzCategoryEntity().setBrowseId(cNode.getBrowseId()).setName(cNode.getName());
            }

            parent.addChild(c);
        }

        categoryRepo.save(parent);
    }

    private void sleepInSeconds(int n){
        try {
            Thread.sleep(1000*n);
        }catch (Exception ex) {
        }
    }
}
