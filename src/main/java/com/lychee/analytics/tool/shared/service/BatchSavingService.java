package com.lychee.analytics.tool.shared.service;


import com.lychee.analytics.tool.utl.ListUtl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * this class will save objects in keywords to db in a batch mode
 *   to detect duplicate items, only half of items will be saved each time.
 *   unsaved objects will still be in keywords
 *
 * @param <T>
 */
public abstract class BatchSavingService<T,ID extends Serializable> {
    private static Logger logger = LoggerFactory.getLogger(BatchSavingService.class);
    private static int batchSize =500;
    @Autowired
    private CrudRepository<T,ID> repo;
    private Class<?> clazz;


    public void setClazz(Class<?> clazz) {
        this.clazz = clazz;
    }

    /**
     * in periodically saving, we only save half of the keywords to.
     *   the not saved recent ones are used to detect duplicates
     *
     * @param objectList
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void batchSave(List<T> objectList) {
        if (objectList.size() >= batchSize){
            List<T> toSave= new ArrayList<>();
            List<T> toKeep = new ArrayList<>();
            ListUtl.splitListByMidIndex(objectList, batchSize / 2 - 1, toSave, toKeep);
            saveObjectsToDb(toSave);
            objectList.clear();
            objectList.addAll(toKeep);
        }
    }

    /**
     * flush all the objects to db
     *
     * @param objectList
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void flush(List<T> objectList) {
        saveObjectsToDb(objectList);
        objectList.clear();
    }

    /**
     * save objects in objectList to db
     *
     * @param objectList
     */
    private void saveObjectsToDb(List<T> objectList) {
        logger.info(String.format("%s %s were stored to db", objectList.size(), this.clazz.getSimpleName()));
        objectList.forEach(k -> repo.save(k));
    }



}
