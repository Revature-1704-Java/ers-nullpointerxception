package com.nullpointerxception.util;

import java.util.List;

public interface Crud<T> {
	
	public List<T> getAll(); 
	
	public T getById(int id);
	
	
	public void insert(T t);

}
