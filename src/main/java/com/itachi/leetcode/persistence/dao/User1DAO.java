package com.itachi.leetcode.persistence.dao;

import com.itachi.leetcode.persistence.po.User1PO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface User1DAO extends JpaRepository<User1PO, String> {

}