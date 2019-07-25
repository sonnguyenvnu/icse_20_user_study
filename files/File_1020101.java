package com.myimooc.spring.tx.demo4;

import org.springframework.transaction.annotation.Transactional;

/**
 * 转账�?务实现
 *
 * @author zc
 * @version v1.0 2017-02-16
 */
@Transactional(rollbackFor = Exception.class)
public class AccountServiceImpl implements AccountService {

    private AccountDao accountDao;

    @Override
    public void transfer(String out, String in, Double money) {
        accountDao.outMoney(out, money);
        //int i = 1/0;
        accountDao.inMoney(in, money);

    }

    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

}
