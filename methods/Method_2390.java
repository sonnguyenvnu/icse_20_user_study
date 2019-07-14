/** 
 * ??????
 * @param offset
 * @param limit
 * @return
 */
public Map getActiveSessions(int offset,int limit){
  Map sessions=new HashMap();
  Jedis jedis=RedisUtil.getJedis();
  long total=jedis.llen(ZHENG_UPMS_SERVER_SESSION_IDS);
  List<String> ids=jedis.lrange(ZHENG_UPMS_SERVER_SESSION_IDS,offset,(offset + limit - 1));
  List<Session> rows=new ArrayList<>();
  for (  String id : ids) {
    String session=RedisUtil.get(ZHENG_UPMS_SHIRO_SESSION_ID + "_" + id);
    if (null == session) {
      RedisUtil.lrem(ZHENG_UPMS_SERVER_SESSION_IDS,1,id);
      total=total - 1;
      continue;
    }
    rows.add(SerializableUtil.deserialize(session));
  }
  jedis.close();
  sessions.put("total",total);
  sessions.put("rows",rows);
  return sessions;
}
