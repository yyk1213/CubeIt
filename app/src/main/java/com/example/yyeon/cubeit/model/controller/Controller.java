package com.example.yyeon.cubeit.model.controller;

public interface Controller<M> {
  // CRUD
  void copyToRealmOrUpdate(M object);  // Create / Update
  M get();  // Read
  void deleteAll();  // Delete
}
