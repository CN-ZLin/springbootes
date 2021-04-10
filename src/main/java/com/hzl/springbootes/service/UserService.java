package com.hzl.springbootes.service;

import com.hzl.springbootes.dao.UserDao;
import com.hzl.springbootes.entity.User;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author HZLin
 * @description
 * @date 2021/4/8
 */
@Service
public class UserService {

//    @Autowired
    @Resource
    private UserDao userDao;

    public void add(){

        User u = new User("1","lin","男","白马围",20);
        u.setId(null);
        userDao.save(u);

        //批量添加
        //List<User> list = new ArrayList<>();
        //list.add(user);
        //userDao.addAll(list);

    }

    public void update(User user){

        //elasticsearch中本没有修改，它是先删除再新增
        //修改和新增是同一个接口，区分的依据就是id。

        userDao.save(user);

    }

    public List<User> listAll(){
        List<User> userList = new ArrayList<>();
//        list = userDao.listAll();
        //查询所有
//        Iterable<User> it = userDao.findAll();

        //查询所有（排序）
        Iterable<User> it = userDao.findAll(Sort.by("age").descending());
        Iterator<User> iterator = it.iterator();
        while (iterator.hasNext()) {
            userList.add(iterator.next());
        }

        //2 分页查找(从0开始)
//        Page<User> page = userDao.findAll(PageRequest.of(0, 5));
//        for (User user : page) {
//            userList.add(user);
//        }

        return userList;
    }


    public List<User> find() {
        List<User> list = new ArrayList<>();

        // 构建查询条件
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        // 添加基本分词查询
//        queryBuilder.withQuery(QueryBuilders.matchQuery("name", "lin"));
//        queryBuilder.withQuery(QueryBuilders.termQuery("name", "lin"));
//        queryBuilder.withQuery(QueryBuilders.fuzzyQuery("name", "lin"));

//        queryBuilder.withQuery(QueryBuilders.boolQuery()
//                .must(QueryBuilders.termQuery("name", "lin"))
//                .must(QueryBuilders.termQuery("address", "白马围")));

//        queryBuilder.withQuery(QueryBuilders.rangeQuery("age").from(20).to(30));

        // 排序
        queryBuilder.withQuery(QueryBuilders.termQuery("name", "lin"));
        queryBuilder.withSort(SortBuilders.fieldSort("age").order(SortOrder.ASC));

        // 搜索，获取结果
        Page<User> page = this.userDao.search(queryBuilder.build());
        // 总条数
        long total = page.getTotalElements();
        System.out.println("total = " + total);

        for (User user : page) {
            list.add(user);
        }

        return list;
    }

}
