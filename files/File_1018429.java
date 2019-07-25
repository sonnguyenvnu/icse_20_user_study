package com.xiaolyuh.service.impl;

import com.github.xiaolyuh.annotation.*;
import com.xiaolyuh.entity.Person;
import com.xiaolyuh.repository.PersonRepository;
import com.xiaolyuh.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

@Service
public class PersonServiceImpl implements PersonService {
    Logger logger = LoggerFactory.getLogger(PersonServiceImpl.class);

    @Autowired
    PersonRepository personRepository;

    @Override
    @CachePut(value = "people", key = "#person.id", depict = "ç”¨æˆ·ä¿¡æ?¯ç¼“å­˜")
    public Person save(Person person) {
        Person p = personRepository.save(person);
        logger.info("ä¸ºidã€?keyä¸º:" + p.getId() + "æ•°æ?®å?šäº†ç¼“å­˜");
        return p;
    }

    @Override
    @CacheEvict(value = "people", key = "#id")//2
    public void remove(Long id) {
        logger.info("åˆ é™¤äº†idã€?keyä¸º" + id + "çš„æ•°æ?®ç¼“å­˜");
        //è¿™é‡Œä¸?å?šå®žé™…åˆ é™¤æ“?ä½œ
    }

    @Override
    @CacheEvict(value = "people", allEntries = true)//2
    public void removeAll() {
        logger.info("åˆ é™¤äº†æ‰€æœ‰ç¼“å­˜çš„æ•°æ?®ç¼“å­˜");
        //è¿™é‡Œä¸?å?šå®žé™…åˆ é™¤æ“?ä½œ
    }

    @Override
    @Cacheable(value = "'people' + ':' + #person.id", key = "#person.id", depict = "ç”¨æˆ·ä¿¡æ?¯ç¼“å­˜",
            firstCache = @FirstCache(expireTime = 4),
            secondaryCache = @SecondaryCache(expireTime = 15, preloadTime = 8, forceRefresh = true))
    public Person findOne(Person person) {
        Person p = personRepository.findOne(Example.of(person));
        logger.info("ä¸ºidã€?keyä¸º:" + p.getId() + "æ•°æ?®å?šäº†ç¼“å­˜");
        return p;
    }

    @Override
    @Cacheable(value = "people1", key = "#person.id", depict = "ç”¨æˆ·ä¿¡æ?¯ç¼“å­˜1",
            firstCache = @FirstCache(expireTime = 4),
            secondaryCache = @SecondaryCache(expireTime = 15, preloadTime = 8, forceRefresh = true))
    public Person findOne1(Person person) {
        Person p = personRepository.findOne(Example.of(person));
        logger.info("ä¸ºidã€?keyä¸º:" + p.getId() + "æ•°æ?®å?šäº†ç¼“å­˜");
        return p;
    }
}
