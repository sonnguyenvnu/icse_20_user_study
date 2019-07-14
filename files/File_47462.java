package com.abel.service;

import com.abel.bean.Person;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by yangyibo on 2018/6/29.
 * 调用person service的断路器
 */
@Service
public class PersonHystrixService {

    @Autowired
    PersonService personService;

    @HystrixCommand(fallbackMethod = "fallbackSave") //使用HystrixCommand的fallbackMethod�?�数指定，调用失败的时候调用�?�备方法 fallbackMethod
    public List<Person> save(String name) {
        return personService.save(name);
    }

    public List<Person> fallbackSave(String name){
        List<Person> list = new ArrayList<Person>();
        Person p = new Person(name+"没有�?存�?功，Person Service 故障");
        list.add(p);
        return list;
    }
}
