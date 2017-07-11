package com.lychee.analytics.tool.amazon.service;

import com.lychee.analytics.tool.amazon.entity.AmzKeywordEntity;
import com.lychee.analytics.tool.shared.service.BatchSavingService;
import org.springframework.stereotype.Service;

@Service
public class AmzKeywordBatchSavingService extends BatchSavingService<AmzKeywordEntity,Long> {
    public AmzKeywordBatchSavingService(){
        this.setClazz(AmzKeywordEntity.class);
    }
}
