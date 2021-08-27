package com.shawncurrie.techblogs.io.repository;

import com.shawncurrie.techblogs.io.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends CrudRepository<UserEntity, Integer> {

    UserEntity findById(int id);

}
