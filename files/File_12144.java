package com.geekq.admin.service.impl;

import com.geekq.admin.entity.Account;
import com.geekq.admin.mapper.AccountMapper;
import com.geekq.admin.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("accountServiceImpl")
public class AccountServiceImpl implements IAccountService {

	@Autowired
	private AccountMapper accountMapper;

	@Override
	public void update(Account account) {
		int ret = accountMapper.updateByPrimaryKey(account);
//		if (ret <= 0) {
//			throw new RuntimeException("Account对象:" + account.getId()
//					+ " �?观�?失败!");
//		}
	}

	@Override
	public Account get(Long id) {
		Account account = accountMapper.selectByPrimaryKey(id);
//		if (!account.checkAbstractInfo()) {
//			throw new RuntimeException("账户信�?�被篡改:" + id);
//		}
		return account;
	}

	/**
	 * �?建account表的摘�?信�?�
	 */
	@Override
	public void recreateAbstractInfo() {
		List<Account> accounts = this.accountMapper.selectAll();
		for (Account account : accounts) {
			this.accountMapper.updateByPrimaryKey(account);
		}
	}

	@Override
	public List<Account> listAll() {
		return this.accountMapper.selectAll();
	}

}
