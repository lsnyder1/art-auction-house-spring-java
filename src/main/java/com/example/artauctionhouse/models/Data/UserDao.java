package com.example.artauctionhouse.models.Data;

import com.example.artauctionhouse.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;


/**
 * Created by lonny on 11/11/2017.
 */
@Transactional
@Repository
public interface UserDao extends CrudRepository<User, Integer> {
}
