public static List<String> getCode(int appType,long appId){
  List<String> list=null;
switch (appType) {
case ConstUtils.CACHE_REDIS_SENTINEL:
{
      list=new ArrayList<String>(redisSentinel);
      break;
    }
case ConstUtils.CACHE_REDIS_STANDALONE:
{
    list=new ArrayList<String>(redisStandalone);
    break;
  }
case ConstUtils.CACHE_TYPE_REDIS_CLUSTER:
{
  list=new ArrayList<String>(redisCluster);
  break;
}
}
if (list != null && list.size() > 0) {
if (!list.get(0).contains("appId =")) {
list.add(0,"long appId = " + appId + ";");
}
}
return list;
}
