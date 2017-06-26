package com.litchi.analytics.tool.amazon.service;


import com.litchi.analytics.tool.amazon.entity.AmzKeywordEntity;
import com.litchi.analytics.tool.amazon.help.AutoCompleteHelp;
import com.litchi.analytics.tool.shared.service.BatchSavingService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class AmzAutoCompleteService {
    @Autowired
    private BatchSavingService<AmzKeywordEntity,Long> batchSavingService;


    /**
     * search keywords starting with partial
     * assume partial have not been tested again amazon
     *
     * @param searchBase
     * @param result
     */
    public void SearchKeyword(String searchBase, List<AmzKeywordEntity> result, long max) throws Exception {
        if (StringUtils.isBlank(searchBase)) {
            searchBase = "a";
        }
        doSearch(searchBase, result, max);
        batchSavingService.flush(result);
    }


    private void doSearch(String searchBase, List<AmzKeywordEntity> result, long max) throws Exception {

       while(searchBase!=null) {
           List<String> matches = AutoCompleteHelp.getAutoCompleteMatches(searchBase);
           addMatchedKeywords(searchBase, result, matches, max);
           batchSavingService.batchSave(result);


           if (result.size() >= max) {
               return;
           }

           //amazon auto complete returns at most 10 records
           if(matches.size() >=10) {
               searchBase = getNextInSearchSpace(searchBase,false);
           } else {
               searchBase = getNextInSearchSpace(searchBase, true);
           }
       }
    }


    public String getNextInSearchSpace(String searchBase, boolean ignoreRight) {
        if (! ignoreRight) {
            return searchBase + " a";
        }

        //else, we should ignore searching right

        //we are done
        if (StringUtils.equals("z", searchBase)) {
            return null;
        }


        int len = searchBase.length();
        char lastChar = searchBase.charAt(len - 1);

        if (lastChar ==' ') {
            return searchBase.substring(0, len-1) + 'a';

        } else if (lastChar =='z') {
            String sub = searchBase.substring(0, len-1);
            return getNextInSearchSpace(sub, true);

        } else  {
            char next  = (char) ((int)lastChar +1);
            return searchBase.substring(0, len-1) + next;
        }
    }



    /**
     * max is used to limit of keyword, only for testing purpose
     *
     * @param result
     * @param matches
     * @param max
     */
    private void addMatchedKeywords(String searchBase, List<AmzKeywordEntity> result, List<String> matches, long max) {
        for(String m: matches) {
            String value = trim(m);
            AmzKeywordEntity keyword = new AmzKeywordEntity().setWord(value).setSearch(searchBase);

            if (!result.contains(keyword) && result.size()<max) {
                result.add(keyword);
            }
        }
    }

    private String trim(String match){
        String value = match.toLowerCase().trim();
        if (value.charAt(0) == '"' && value.charAt(value.length()-1) =='"') {
            value = value.substring(1, value.length()-1);
        }

        return value.trim();
    }



}
