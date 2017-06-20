package com.litchi.analytics.tool.amazon.service;

import com.litchi.analytics.tool.amazon.entity.AmzCategoryEntity;
import com.litchi.analytics.tool.amazon.help.BrowseNodeHelp;
import com.litchi.analytics.tool.amazon.model.AmzBrowseNode;
import com.litchi.analytics.tool.amazon.repo.AmzCategoryRepo;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class AmzCategoryServiceTest {
    @Autowired
    private AmzCategoryService categoryService;
    @Autowired
    private AmzCategoryRepo categoryRepo;
    @Test
    public void saveOneBrowseNodeAndChildren(){
        List<AmzBrowseNode> children = new ArrayList<>();
        BrowseNodeHelp.getBrowseNode("172282", children);
        categoryService.saveCategoryAndChildren("172282");
        AmzCategoryEntity entity = categoryRepo.findOne("172282");
        assertNotNull(entity);
        assertEquals(entity.getChildren().size(), children.size());
    }
}
