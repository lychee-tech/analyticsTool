package com.lychee.analytics.tool.amazon.service;

import com.lychee.analytics.tool.amazon.entity.AmzCategoryEntity;
import com.lychee.analytics.tool.amazon.help.BrowseNodeHelp;
import com.lychee.analytics.tool.amazon.model.AmzBrowseNode;
import com.lychee.analytics.tool.amazon.repo.AmzCategoryRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private  static Logger logger = LoggerFactory.getLogger(AmzCategoryService.class);
    @Autowired
    AmzCategoryRepo categoryRepo;


    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public List<AmzBrowseNode> saveCategoryAndChildren(String browseNodeId){
        List<AmzBrowseNode> children = new ArrayList<>();
        sleepInSeconds(10);

        //this line will populate children
        try {
            AmzBrowseNode node = BrowseNodeHelp.getBrowseNode(browseNodeId, children);
            if (node == null) {
                throw new Exception(String.format("amazon return null for BrowseNode %s", browseNodeId));
            }

            saveCategoryAndChildrenRelationShip(node, children);

        } catch (Exception ex){
            logger.error(String.format("failed to retrieve  BrowseNode %s from amazon", browseNodeId), ex);
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
