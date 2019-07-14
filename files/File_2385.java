package com.zheng.upms.client.shiro.listener;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by shuzheng on 2017/2/12.
 */
public class UpmsSessionListener implements SessionListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(UpmsSessionListener.class);

    @Override
    public void onStart(Session session) {
        LOGGER.debug("会�?创建：" + session.getId());
    }

    @Override
    public void onStop(Session session) {
        LOGGER.debug("会�?�?�止：" + session.getId());
    }

    @Override
    public void onExpiration(Session session) {
        LOGGER.debug("会�?过期：" + session.getId());
    }

}
