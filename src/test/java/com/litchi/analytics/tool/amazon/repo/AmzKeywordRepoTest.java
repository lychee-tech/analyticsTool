package com.litchi.analytics.tool.amazon.repo;

import com.litchi.analytics.tool.amazon.entity.AmzKeywordEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AmzKeywordRepoTest {
    @Autowired
    private CrudRepository<AmzKeywordEntity,Long> amzKeywordRepo;

    @Test
    public void testSaveKeyWord(){
        AmzKeywordEntity amzKeyword = new AmzKeywordEntity();
        amzKeyword.setSearch("java").setWord("complete java");
        amzKeywordRepo.save(amzKeyword);
        AmzKeywordEntity saved = amzKeywordRepo.findOne(amzKeyword.getId());
        assertEquals("java", saved.getSearch());
        assertEquals("complete java", saved.getWord());

    }
}
