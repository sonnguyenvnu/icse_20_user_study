package com.us.example.service.Impl;

import com.us.example.bean.Person;
import com.us.example.dao.PersonRepository;
import com.us.example.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * Created by yangyibo on 17/1/13.
 */
@Service
public class DemoServiceImpl implements DemoService {

    @Autowired
    private PersonRepository personRepository;

    @Override
    //@CachePut缓存新增的或更新的数�?�到缓存,其中缓存�??字是 people 。数�?�的key是person的id
    @CachePut(value = "people", key = "#person.id")
    public Person save(Person person) {
        Person p = personRepository.save(person);
        System.out.println("为id�?key为:"+p.getId()+"数�?��?�了缓存");
        return p;
    }

    @Override
    //@CacheEvict 从缓存people中删除key为id 的数�?�
    @CacheEvict(value = "people")
    public void remove(Long id) {
        System.out.println("删除了id�?key为"+id+"的数�?�缓存");
        //这里�?�?�实际删除�?作
    }

    @Override
    //@Cacheable缓存key为person 的id 数�?�到缓存people 中,如果没有指定key则方法�?�数作为key�?存到缓存中。
    @Cacheable(value = "people", key = "#person.id")
    public Person findOne(Person person) {
        Person p = personRepository.findOne(person.getId());
        System.out.println("为id�?key为:"+p.getId()+"数�?��?�了缓存");
        return p;
    }

}
