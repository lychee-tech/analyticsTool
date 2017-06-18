package com.litchi.analytics.tool.amazon.repo;


import com.litchi.analytics.tool.amazon.entity.AmzCategoryEntity;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.Test;

import javax.transaction.Transactional;
import static  org.junit.Assert.*;
import java.util.Set;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class AmzCategoryRepoTest {
    @Autowired
    private AmzCategoryRepo categoryRepo;

    @Test
    public void testSavingCategory(){
        AmzCategoryEntity entity = new AmzCategoryEntity();
        entity.setBrowseId("b1");
        entity.setName("clothes");
        categoryRepo.save(entity);

        AmzCategoryEntity saved = categoryRepo.findOne(entity.getBrowseId());
        assertNotNull(saved);
        assertEquals("clothes", saved.getName());
    }

    @Test
    public void testSavingCategoryRelationship(){
        AmzCategoryEntity entity1 = new AmzCategoryEntity();
        entity1.setBrowseId("parent1");
        entity1.setName("clothes");

        AmzCategoryEntity entity2 = new AmzCategoryEntity();
        entity2.setBrowseId("child1");
        entity2.setName("book");

        AmzCategoryEntity entity3 = new AmzCategoryEntity();
        entity3.setBrowseId("child2");
        entity3.setName("movie");

        entity1.addChild(entity2);
        entity1.addChild(entity3);
        categoryRepo.save(entity1);

        AmzCategoryEntity savedEntity1 = categoryRepo.findOne(entity1.getBrowseId());
        Set<AmzCategoryEntity> children = savedEntity1.getChildren();
        assertEquals(2, children.size());

        AmzCategoryEntity savedEntity2 = categoryRepo.findOne(entity2.getBrowseId());
        Set<AmzCategoryEntity> parents = savedEntity2.getParents();
        assertEquals(1, parents.size());
        AmzCategoryEntity parent = parents.iterator().next();
        assertEquals("parent1", parent.getBrowseId());
    }

}
