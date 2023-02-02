package com.monyba.exchange.service;

import com.monyba.exchange.repository.GenericRepo;

import java.util.List;

public abstract class CrudImpl<T, ID> implements Crud<T, ID> {

  protected abstract GenericRepo<T, ID> getRepository();

  @Override
  public T save(T t) throws Exception {
    return getRepository().save(t);
  }

  @Override
  public T update(T t) throws Exception {
    return getRepository().save(t);
  }

  @Override
  public List<T> findAll() throws Exception {
    return getRepository().findAll();
  }

  @Override
  public T findById(ID id) throws Exception {
    return getRepository().findById(id).orElse(null);
  }

  @Override
  public void delete(ID id) throws Exception {
    getRepository().deleteById(id);
  }

}
