package com.monyba.exchange.service;

import java.util.List;

public interface Crud<T, ID>{

  T save(T t) throws Exception;
  T update(T t) throws Exception;
  List<T> findAll() throws Exception;
  T findById(ID id) throws Exception;
  void delete(ID id) throws Exception;

}
