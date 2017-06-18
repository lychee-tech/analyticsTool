package com.litchi.analytics.tool.amazon.repo;

import com.litchi.analytics.tool.amazon.entity.AmzCategoryEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface AmzCategoryRepo extends CrudRepository<AmzCategoryEntity, String> {
}
