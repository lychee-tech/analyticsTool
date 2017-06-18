package com.litchi.analytics.tool.amazon.service;

import com.litchi.analytics.tool.amazon.entity.AmzKeywordEntity;
import com.litchi.analytics.tool.shared.service.BatchSavingService;
import org.springframework.stereotype.Service;

@Service
public class AmzKeywordBatchSavingService extends BatchSavingService<AmzKeywordEntity,Long> {
    public AmzKeywordBatchSavingService(){
        this.setClazz(AmzKeywordEntity.class);
    }
}
