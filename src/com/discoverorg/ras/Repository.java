package com.discoverorg.ras;

import java.io.Serializable;
import java.util.List;

public interface Repository<T, ID extends Serializable> {
	
	T findOne(ID id);
	
	List<T> findAll();
	
	Long count();
	 
    void delete(ID id);
 
    boolean exists(String primaryKey);
    
    void save(T entity);
    
    void save(List<T> entityList);

}
