/** 
 * ??appId??RedisSentinel????
 * @param appId
 */
@RequestMapping(value="/redis/sentinel/{appId}.json") public void getSentinelAppById(HttpServletRequest request,@PathVariable long appId,Model model){
  if (!handleRedisApp(appId,request,model,ConstUtils.CACHE_REDIS_SENTINEL,false)) {
    return;
  }
  getRedisSentinelInfo(request,appId,model);
}
