package com.kuropatin.zenbooking.work.imitation.repository;

import com.kuropatin.zenbooking.model.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;

@Repository
public interface FakeUserRepository extends CrudRepository<User, Long> {

    @Modifying
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = SQLException.class)
    @Query(value = "INSERT INTO fake_users (user_id) values (?1)", nativeQuery = true)
    void createRecord(Long id);
}