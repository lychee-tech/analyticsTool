package com.litchi.analytics.tool.amazon.service;

import com.litchi.analytics.tool.amazon.entity.AmzKeywordEntity;
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
        autoCompleteService.SearchKeyword("", keywords,2);
        assertEquals(2, keywords.size());
    }

    @Test
    public void testGetSearchSpaceOfEmptyString(){
        List<String> space = autoCompleteService.getNextSearchSpace(null);
        assertEquals(26, space.size());
    }

    @Test
    public void testGetSearchSpaceOfOneChar(){
        List<String> space = autoCompleteService.getNextSearchSpace("a");
        assertEquals(52, space.size());
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
        autoCompleteService.SearchKeyword("java moss a", keywords,1000);
    }

}
