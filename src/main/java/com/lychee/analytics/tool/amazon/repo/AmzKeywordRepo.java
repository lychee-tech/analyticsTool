package com.lychee.analytics.tool.amazon.repo;

import com.lychee.analytics.tool.amazon.entity.AmzKeywordEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AmzKeywordRepo extends CrudRepository<AmzKeywordEntity, Long>{
}
