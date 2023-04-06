package com.unitech.repository;

import com.unitech.entity.User;
import com.unitech.entity.UserAccounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccounts,Long> {
    List<UserAccounts> findByUser(User userId);
}
