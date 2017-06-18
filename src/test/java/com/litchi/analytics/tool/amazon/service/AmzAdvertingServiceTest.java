package com.litchi.analytics.tool.amazon.service;


import com.litchi.analytics.tool.amazon.model.AmzProductPage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;


@RunWith(SpringRunner.class)
@SpringBootTest
public class AmzAdvertingServiceTest {
    @Autowired
    private AmzAdvertingService amzAdvertingService;
    @Test
    public void testSearchJava(){
        AmzProductPage page = amzAdvertingService.searchProduct("java", 1);

        assertNotNull(page);
        assertNotNull(page.getProducts());
        assertTrue(page.getProducts().size()>1);
    }
}
