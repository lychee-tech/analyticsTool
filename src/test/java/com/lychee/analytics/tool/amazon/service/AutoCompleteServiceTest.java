package com.lychee.analytics.tool.amazon.service;

import com.lychee.analytics.tool.amazon.entity.AmzKeywordEntity;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Matchers.anyListOf;
import static org.mockito.Mockito.doNothing;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AutoCompleteServiceTest {
    @Autowired
    private AmzAutoCompleteService autoCompleteService;
    @MockBean
    private AmzKeywordBatchSavingService batchSavingService=null;

    @Before
    public void setup(){
        doNothing().when(batchSavingService).flush(anyListOf(AmzKeywordEntity.class));
        doNothing().when(batchSavingService).batchSave(anyListOf(AmzKeywordEntity.class));
    }



    @Test
    public void aSimpleSearch() throws Exception {
        List<AmzKeywordEntity> keywords = new ArrayList<>();
        autoCompleteService.SearchKeyword("", keywords, 2);
        assertEquals(2, keywords.size());
    }

    @Test
    public void getNextInRightInSearchSpace(){
        String next = autoCompleteService.getNextInSearchSpace("abc",false);
        assertEquals("abc a", next);
    }

    @Test
    public void getNextInUpInSearchSpace(){
        String next = autoCompleteService.getNextInSearchSpace("abc", true);
        assertEquals("abd", next);
    }

    @Test
    public void getNextInUpInSearchSpace2(){
        String next = autoCompleteService.getNextInSearchSpace("abz", true);
        assertEquals("ac", next);
    }

    @Test
    public void getNextInUpInSearchSpace3(){
        String next = autoCompleteService.getNextInSearchSpace("azz", true);
        assertEquals("b", next);
    }

    @Test
    public void getNextInUpInSearchSpace4(){
        String next = autoCompleteService.getNextInSearchSpace("zzz", true);
        assertNull(next);
    }

    @Test
    public void getNextInUpInSearchSpace5(){
        String next = autoCompleteService.getNextInSearchSpace("z a z", true);
        assertEquals("z aa", next);
    }
    @Test
    public void getNextInUpInSearchSpace6(){
        String next = autoCompleteService.getNextInSearchSpace("z azz", true);
        assertEquals("z b", next);
    }


    @Test
    public void testSimpleKeywordSearch() throws Exception {
        List<AmzKeywordEntity> keywords = new ArrayList<>();
        autoCompleteService.SearchKeyword("java", keywords, 10);
    }


    /**
     *  this test case approve for search result = 4. increase search space will not find  more matches
     *
     * @throws Exception
     */
    @Test
    public void testShortMatches() throws Exception {
        List<AmzKeywordEntity> keywords = new ArrayList<>();
        autoCompleteService.SearchKeyword("java moss a", keywords,10);
    }

}
