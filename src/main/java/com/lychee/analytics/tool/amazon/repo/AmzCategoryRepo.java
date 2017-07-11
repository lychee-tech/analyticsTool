package com.lychee.analytics.tool.amazon.repo;

import com.lychee.analytics.tool.amazon.entity.AmzCategoryEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AmzCategoryRepo extends CrudRepository<AmzCategoryEntity, String> {
}
