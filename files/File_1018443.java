package com.xiaolyuh.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.remoting.RemoteAccessException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import java.time.LocalTime;

@Service
public class RetryService {
    private final static Logger logger = LoggerFactory.getLogger(RetryService.class);

    @Retryable(value = {RemoteAccessException.class}, maxAttempts = 3, backoff = @Backoff(delay = 500l, multiplier = 1))
    public void retry() {
        logger.info(LocalTime.now() + " 执行业务�?作...");
        throw new RemoteAccessException("RPC调用异常");
    }

    // @Retryable注解方法返回值是void，@Recover�?会生效
    @Recover
    public void recover(RemoteAccessException e) {
        logger.info(e.getMessage());
    }
}
