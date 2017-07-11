package com.lychee.analytics.tool.amazon.service;

import com.lychee.analytics.tool.amazon.entity.AmzCategoryEntity;
import com.lychee.analytics.tool.amazon.model.AmzBrowseNode;
import com.lychee.analytics.tool.amazon.repo.AmzCategoryRepo;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.Test;
import java.util.Map;

import javax.transaction.Transactional;
import java.util.HashMap;
import static  org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class AmzCategoryMapServiceTest {
    @Autowired
    private AmzCategoryMapService categoryMapService;
    @Autowired
    private AmzCategoryRepo categoryRepo;

    @Test
    public void testMapFromOneTopNode(){
        Map<String, AmzBrowseNode> topNodes = new HashMap<>();
        topNodes.put("11056211", new AmzBrowseNode("11056211","Bath",false));
        categoryMapService.setTopNodes(topNodes);
        categoryMapService.buildCategoryRelationMap();
        AmzCategoryEntity parent = categoryRepo.findOne("11056211");
        assertTrue(parent.getChildren().size()>0);
    }
}
