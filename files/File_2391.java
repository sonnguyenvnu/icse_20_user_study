package com.zheng.upms.client.shiro.session;

import com.zheng.common.util.RedisUtil;
import com.zheng.upms.client.util.SerializableUtil;
import com.zheng.upms.common.constant.UpmsConstant;
import org.apache.commons.lang.ObjectUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.ValidatingSession;
import org.apache.shiro.session.mgt.eis.CachingSessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;

import java.io.Serializable;
import java.util.*;

/**
 * 基于redis的sessionDao，缓存共享session
 * Created by shuzheng on 2017/2/23.
 */
public class UpmsSessionDao extends CachingSessionDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(UpmsSessionDao.class);
    // 会�?key
    private final static String ZHENG_UPMS_SHIRO_SESSION_ID = "zheng-upms-shiro-session-id";
    // 全局会�?key
    private final static String ZHENG_UPMS_SERVER_SESSION_ID = "zheng-upms-server-session-id";
    // 全局会�?列表key
    private final static String ZHENG_UPMS_SERVER_SESSION_IDS = "zheng-upms-server-session-ids";
    // code key
    private final static String ZHENG_UPMS_SERVER_CODE = "zheng-upms-server-code";
    // 局部会�?key
    private final static String ZHENG_UPMS_CLIENT_SESSION_ID = "zheng-upms-client-session-id";
    // �?�点�?�一个code所有局部会�?key
    private final static String ZHENG_UPMS_CLIENT_SESSION_IDS = "zheng-upms-client-session-ids";

    @Override
    protected Serializable doCreate(Session session) {
        Serializable sessionId = generateSessionId(session);
        assignSessionId(session, sessionId);
        RedisUtil.set(ZHENG_UPMS_SHIRO_SESSION_ID + "_" + sessionId, SerializableUtil.serialize(session), (int) session.getTimeout() / 1000);
        LOGGER.debug("doCreate >>>>> sessionId={}", sessionId);
        return sessionId;
    }

    @Override
    protected Session doReadSession(Serializable sessionId) {
        String session = RedisUtil.get(ZHENG_UPMS_SHIRO_SESSION_ID + "_" + sessionId);
        LOGGER.debug("doReadSession >>>>> sessionId={}", sessionId);
        return SerializableUtil.deserialize(session);
    }

    @Override
    protected void doUpdate(Session session) {
        // 如果会�?过期/�?�止 没必�?�?更新了
        if(session instanceof ValidatingSession && !((ValidatingSession)session).isValid()) {
            return;
        }
        // 更新session的最�?�一次访问时间
        UpmsSession upmsSession = (UpmsSession) session;
        UpmsSession cacheUpmsSession = (UpmsSession) doReadSession(session.getId());
        if (null != cacheUpmsSession) {
            upmsSession.setStatus(cacheUpmsSession.getStatus());
            upmsSession.setAttribute("FORCE_LOGOUT", cacheUpmsSession.getAttribute("FORCE_LOGOUT"));
        }
        RedisUtil.set(ZHENG_UPMS_SHIRO_SESSION_ID + "_" + session.getId(), SerializableUtil.serialize(session), (int) session.getTimeout() / 1000);
        // 更新ZHENG_UPMS_SERVER_SESSION_ID�?ZHENG_UPMS_SERVER_CODE过期时间 TODO
        LOGGER.debug("doUpdate >>>>> sessionId={}", session.getId());
    }

    @Override
    protected void doDelete(Session session) {
        String sessionId = session.getId().toString();
        String upmsType = ObjectUtils.toString(session.getAttribute(UpmsConstant.UPMS_TYPE));
        if ("client".equals(upmsType)) {
            // 删除局部会�?和�?�一code注册的局部会�?
            String code = RedisUtil.get(ZHENG_UPMS_CLIENT_SESSION_ID + "_" + sessionId);
            Jedis jedis = RedisUtil.getJedis();
            jedis.del(ZHENG_UPMS_CLIENT_SESSION_ID + "_" + sessionId);
            jedis.srem(ZHENG_UPMS_CLIENT_SESSION_IDS + "_" + code, sessionId);
            jedis.close();
        }
        if ("server".equals(upmsType)) {
            // 当�?全局会�?code
            String code = RedisUtil.get(ZHENG_UPMS_SERVER_SESSION_ID + "_" + sessionId);
            // 清除全局会�?
            RedisUtil.remove(ZHENG_UPMS_SERVER_SESSION_ID + "_" + sessionId);
            // 清除code校验值
            RedisUtil.remove(ZHENG_UPMS_SERVER_CODE + "_" + code);
            // 清除所有局部会�?
            Jedis jedis = RedisUtil.getJedis();
            Set<String> clientSessionIds = jedis.smembers(ZHENG_UPMS_CLIENT_SESSION_IDS + "_" + code);
            for (String clientSessionId : clientSessionIds) {
                jedis.del(ZHENG_UPMS_CLIENT_SESSION_ID + "_" + clientSessionId);
                jedis.srem(ZHENG_UPMS_CLIENT_SESSION_IDS + "_" + code, clientSessionId);
            }
            LOGGER.debug("当�?code={}，对应的注册系统个数：{}个", code, jedis.scard(ZHENG_UPMS_CLIENT_SESSION_IDS + "_" + code));
            jedis.close();
            // 维护会�?id列表，�??供会�?分页管�?�
            RedisUtil.lrem(ZHENG_UPMS_SERVER_SESSION_IDS, 1, sessionId);
        }
        // 删除session
        RedisUtil.remove(ZHENG_UPMS_SHIRO_SESSION_ID + "_" + sessionId);
        LOGGER.debug("doUpdate >>>>> sessionId={}", sessionId);
    }

    /**
     * 获�?�会�?列表
     * @param offset
     * @param limit
     * @return
     */
    public Map getActiveSessions(int offset, int limit) {
        Map sessions = new HashMap();
        Jedis jedis = RedisUtil.getJedis();
        // 获�?�在线会�?总数
        long total = jedis.llen(ZHENG_UPMS_SERVER_SESSION_IDS);
        // 获�?�当�?页会�?详情
        List<String> ids = jedis.lrange(ZHENG_UPMS_SERVER_SESSION_IDS, offset, (offset + limit - 1));
        List<Session> rows = new ArrayList<>();
        for (String id : ids) {
            String session = RedisUtil.get(ZHENG_UPMS_SHIRO_SESSION_ID + "_" + id);
            // 过滤redis过期session
            if (null == session) {
                RedisUtil.lrem(ZHENG_UPMS_SERVER_SESSION_IDS, 1, id);
                total = total - 1;
                continue;
            }
             rows.add(SerializableUtil.deserialize(session));
        }
        jedis.close();
        sessions.put("total", total);
        sessions.put("rows", rows);
        return sessions;
    }

    /**
     * 强制退出
     * @param ids
     * @return
     */
    public int forceout(String ids) {
        String[] sessionIds = ids.split(",");
        for (String sessionId : sessionIds) {
            // 会�?增加强制退出属性标识，当此会�?访问系统时，判断有该标识，则退出登录
            String session = RedisUtil.get(ZHENG_UPMS_SHIRO_SESSION_ID + "_" + sessionId);
            UpmsSession upmsSession = (UpmsSession) SerializableUtil.deserialize(session);
            upmsSession.setStatus(UpmsSession.OnlineStatus.force_logout);
            upmsSession.setAttribute("FORCE_LOGOUT", "FORCE_LOGOUT");
            RedisUtil.set(ZHENG_UPMS_SHIRO_SESSION_ID + "_" + sessionId, SerializableUtil.serialize(upmsSession), (int) upmsSession.getTimeout() / 1000);
        }
        return sessionIds.length;
    }

    /**
     * 更改在线状�?
     *
     * @param sessionId
     * @param onlineStatus
     */
    public void updateStatus(Serializable sessionId, UpmsSession.OnlineStatus onlineStatus) {
        UpmsSession session = (UpmsSession) doReadSession(sessionId);
        if (null == session) {
            return;
        }
        session.setStatus(onlineStatus);
        RedisUtil.set(ZHENG_UPMS_SHIRO_SESSION_ID + "_" + session.getId(), SerializableUtil.serialize(session), (int) session.getTimeout() / 1000);
    }

}
