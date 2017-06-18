package com.litchi.analytics.tool.amazon.service;


import com.litchi.analytics.tool.amazon.entity.AmzKeywordEntity;
import com.litchi.analytics.tool.amazon.help.AutoCompleteHelp;
import com.litchi.analytics.tool.shared.service.BatchSavingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        doSearch(searchBase, result, max);
        batchSavingService.flush(result);
    }


    private void doSearch(String searchBase, List<AmzKeywordEntity> result, long max) throws Exception {
        searchBase = searchBase == null? "" : searchBase;

        //found what we need, return
        if (result.size() >= max) {
            return;
        }

        List<String> searchSpace =null;

        if ( searchBase.isEmpty()) {
           searchSpace = getNextSearchSpace(searchBase);

        } else {
            List<String> matches = AutoCompleteHelp.getAutoCompleteMatches(searchBase);
            addMatchedKeywords(searchBase, result, matches, max);
            batchSavingService.batchSave(result);

            //auto complete returns at most 10 records
            if (matches.size()>=10) {
                searchSpace = getNextSearchSpace(searchBase);
            }
        }


        if (searchSpace!=null) {
            for(String s: searchSpace) {
                doSearch(s, result, max);
            }
        }
    }


    /**
     * make it public for testing purpose
     *
     * @param keyword
     * @return
     */
    public List<String> getNextSearchSpace(String keyword) {
        keyword= keyword==null? "": keyword;

        List<String> spaceWithWhitSpace = new ArrayList<String>();
        List<String> space = new ArrayList<String>();

        for(char c='a';c<='z';c++) {
            space.add(keyword + c);

            if (! keyword.isEmpty()) {
                spaceWithWhitSpace.add(keyword+" "+c);
            }
        }

        //keep space in alphabetic order
        spaceWithWhitSpace.addAll(space);
        return spaceWithWhitSpace;
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
