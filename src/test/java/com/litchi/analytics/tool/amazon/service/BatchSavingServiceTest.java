package com.litchi.analytics.tool.amazon.service;

import com.litchi.analytics.tool.amazon.entity.AmzKeywordEntity;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static  org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BatchSavingServiceTest {
    private  static Logger logger = LoggerFactory.getLogger(AmzCategoryService.class);
    @Autowired
    private AmzKeywordBatchSavingService batchSavingService;
    @Autowired
    private CrudRepository<AmzKeywordEntity,Long> repo;

    @Before
    public void setup(){
        batchSavingService.setClazz(AmzKeywordEntity.class);
    }

    @Test
    public void testSaveKeywords() {
        List<AmzKeywordEntity> keywords = new ArrayList<>();
        keywords.add(new AmzKeywordEntity().setSearch("search1").setWord("word1"));
        keywords.add(new AmzKeywordEntity().setSearch("search2").setWord("word2"));
        batchSavingService.batchSave(keywords);
        Object keyword = repo.findOne(1L);

        if (keyword!=null) {
            AmzKeywordEntity e = (AmzKeywordEntity) keyword;
            logger.error(String.format("should be null, but not, %s: %s", e.getSearch(), e.getWord()));
        }

        assertNull(keyword);
        batchSavingService.flush(keywords);
        Object saved = repo.findOne(2L);
        assertNotNull(saved);
        AmzKeywordEntity savedKeyword =(AmzKeywordEntity) saved;
        assertEquals("search2", savedKeyword.getSearch());
        assertEquals("word2", savedKeyword.getWord());

    }
}
