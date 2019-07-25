package com.myimooc.ssh.employee.service.impl;

import java.util.List;

import com.myimooc.ssh.employee.dao.EmployeeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myimooc.ssh.employee.domain.Employee;
import com.myimooc.ssh.employee.domain.PageBean;
import com.myimooc.ssh.employee.service.EmployeeService;

/**
 * 员工管�?�的业务层的实现类
 * @author ZhangCheng on 2017-08-18
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	private EmployeeDao employeeDao;

	/**
	 * 业务层登录方法
	 */
	@Override
	public Employee login(Employee employee) {
		Employee existEmployee = employeeDao.findByUsernameAndPassword(employee);
		return existEmployee;
	}
	
	/**
	 * 分页查询员工信�?�
	 */
	@Override
	public PageBean<Employee> findByPage(Integer currPage) {
		PageBean<Employee> pageBean = new PageBean<Employee>();
		// �?装当�?页数
		pageBean.setCurrPage(currPage);
		// �?装�?页显示记录数
		int pageSize = 3;
		pageBean.setPageSize(pageSize);
		// �?装总记录数
		int totalCount = employeeDao.findCount();
		pageBean.setTotalCount(totalCount);
		// �?装总页数
		double tc = totalCount;
		Double num = Math.ceil(tc / pageSize);
		pageBean.setTotalPage(num.intValue());
		// �?装�?页显示数�?�
		int begin = (currPage - 1) * pageSize;
		List<Employee> list = employeeDao.findByPage(begin,pageSize);
		pageBean.setList(list);
		return pageBean;
	}
	
	/**
	 * �?存员工信�?�
	 */
	@Override
	public void save(Employee employee) {
		employeeDao.save(employee);
		
	}

	@Override
	public Employee findById(Integer eid) {
		return employeeDao.findById(eid);
	}
	
	/**
	 * 修改员工信�?�
	 */
	@Override
	public void update(Employee employee) {
		employeeDao.update(employee);
	}

	/**
	 * 删除员工信�?�
	 */
	@Override
	public void delete(Employee employee) {
		employeeDao.delete(employee);
	}
}
