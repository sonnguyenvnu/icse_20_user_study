package com.myimooc.excel.boot.service;

import java.util.List;

import com.myimooc.excel.boot.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myimooc.excel.boot.domain.model.Student;

/**
 * 学生信�?�业务类
 * @author ZhangCheng on 2017-07-08
 *
 */
@Service
public class StudentService {
	
	@Autowired
	private StudentRepository studentRepository;
	
	public List<Student> list(){
		return studentRepository.findAll();
	}
	
}
