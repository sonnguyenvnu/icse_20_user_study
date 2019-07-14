/** 
 * ??appId??RedisSentinel????(???appkey)
 * @param appId
 */
@RequestMapping(value="/redis/sentinel/safe/{appId}.json") public void getSentinelByAppIdAndKey(HttpServletRequest request,@PathVariable long appId,Model model){
  if (!handleRedisApp(appId,request,model,ConstUtils.CACHE_REDIS_SENTINEL,true)) {
    return;
  }
  getRedisSentinelInfo(request,appId,model);
}
