package com.example.artauctionhouse.models.Data;

import com.example.artauctionhouse.models.Art;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * Created by lonny on 11/30/2017.
 */
@Transactional
@Repository
public interface ArtDao extends CrudRepository<Art,Integer> {
}
