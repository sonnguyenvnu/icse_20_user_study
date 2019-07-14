public static List<String> getSpringConfig(int appType,long appId){
  List<String> list=new ArrayList<String>();
switch (appType) {
case ConstUtils.CACHE_REDIS_SENTINEL:
{
      list.addAll(redisSentinelSpring);
      break;
    }
case ConstUtils.CACHE_REDIS_STANDALONE:
{
    break;
  }
case ConstUtils.CACHE_TYPE_REDIS_CLUSTER:
{
  list.addAll(redisClusterSpring);
  break;
}
}
if (list != null && list.size() > 0) {
for (int i=0; i < list.size(); i++) {
String line=list.get(i);
if (line != null && line.contains(springAppId)) {
  line=line.replace(springAppId,String.valueOf(appId));
  list.set(i,line);
}
}
}
return null;
}
