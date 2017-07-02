package com.litchi.analytics.tool.sonar.help;


import com.litchi.analytics.tool.sonar.model.SonarMatchResult;
import com.litchi.analytics.tool.sonar.model.SonarSearchRequest;
import org.junit.Test;
import static  org.junit.Assert.*;

public class SonarToolHelpTest {

    @Test
    public void searchLedInSonarTool(){
        SonarSearchRequest request = new SonarSearchRequest().setCountry("us").setKeyword("led");
        SonarMatchResult result = SonarToolHelp.getSonarToolMatches(request);
        assertNotNull(request);
        assertNotNull(result.getListWithScore());
    }
}
