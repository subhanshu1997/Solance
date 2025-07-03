package com.solance.banking.dao;

import com.solance.banking.model.UserTable;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserTable, String> {



}
