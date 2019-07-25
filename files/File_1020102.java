package com.myimooc.spring.tx.mydemo1.service.impl;

import com.myimooc.spring.tx.mydemo1.dao.AccountDao;
import com.myimooc.spring.tx.mydemo1.service.AccountService;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * 转账业务功能实现类。
 * @author zhangcheng
 * @version v1.0 2017-02-16
 */
public class AccountServiceImpl implements AccountService {
    
    /**
     * 转账案例DAO接�?�
     */
    private AccountDao accountDao;
    
    public void setAccountDao(AccountDao accountDao){
        this.accountDao = accountDao;
    }
    
    private TransactionTemplate transactionTemplate;
    public void setTransactionTemplate(TransactionTemplate transactionTemplate){
        this.transactionTemplate = transactionTemplate;
    }

    /**
     * 功能：转账功能。
     * @param out 转出账�?�
     * @param in 转入账�?�
     * @param money 转账金�?
     */
    @Override
    public void transfer(final String out,final String in,final Double money) {
        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                accountDao.outMoney(out, money);
                accountDao.inMoney(in, money);
            }
        });
    }

}
