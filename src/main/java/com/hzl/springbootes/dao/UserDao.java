package com.hzl.springbootes.dao;

import com.hzl.springbootes.entity.User;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author HZLin
 * @description
 * @date 2021/4/8
 */
@Repository
public interface UserDao extends ElasticsearchRepository<User,String> {

//    public void add(User user);

//    List<User> addAll(User user);

//    public void update(User user);

//    public List<User> listAll();


}
