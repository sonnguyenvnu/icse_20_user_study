private void statusReport(){
  final JSONObject ret=new JSONObject();
  ret.put(Common.ONLINE_VISITOR_CNT,optionQueryService.getOnlineVisitorCount());
  ret.put(Common.ONLINE_MEMBER_CNT,optionQueryService.getOnlineMemberCount());
  ret.put(Common.ONLINE_CHAT_CNT,ChatroomChannel.SESSIONS.size());
  ret.put(Common.ARTICLE_CHANNEL_CNT,ArticleChannel.SESSIONS.size());
  ret.put(Common.ARTICLE_LIST_CHANNEL_CNT,ArticleListChannel.SESSIONS.size());
  ret.put(Common.THREAD_CNT,Symphonys.getActiveThreadCount() + "/" + Symphonys.getMaxThreadCount());
  ret.put(Common.DB_CONN_CNT,Connections.getActiveConnectionCount() + "/" + Connections.getTotalConnectionCount() + "/" + Connections.getMaxConnectionCount());
  ret.put(Keys.Runtime.RUNTIME_CACHE,Latkes.getRuntimeCache().name());
  ret.put(Keys.Runtime.RUNTIME_DATABASE,Latkes.getRuntimeDatabase().name());
  ret.put(Keys.Runtime.RUNTIME_MODE,Latkes.getRuntimeMode().name());
  final JSONObject memory=new JSONObject();
  ret.put("memory",memory);
  final int mb=1024 * 1024;
  final Runtime runtime=Runtime.getRuntime();
  memory.put("total",runtime.totalMemory() / mb);
  memory.put("free",runtime.freeMemory() / mb);
  memory.put("used",(runtime.totalMemory() - runtime.freeMemory()) / mb);
  memory.put("max",runtime.maxMemory() / mb);
  LOGGER.info(ret.toString(4));
}
