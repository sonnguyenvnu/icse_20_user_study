public static String getRestAPI(int appType,long appId){
  String redisGoodVersion=getGoodVersion();
  String appTypePath="";
switch (appType) {
case ConstUtils.CACHE_REDIS_SENTINEL:
{
      appTypePath="sentinel";
      break;
    }
case ConstUtils.CACHE_REDIS_STANDALONE:
{
    appTypePath="standalone";
    break;
  }
case ConstUtils.CACHE_TYPE_REDIS_CLUSTER:
{
  appTypePath="cluster";
  break;
}
}
return ConstUtils.CC_DOMAIN + "/cache/client/redis/" + appTypePath + "/" + appId + ".json?clientVersion=" + redisGoodVersion;
}
