/** 
 * ??appId??RedisStandalone????
 * @param appId
 */
@RequestMapping(value="/redis/standalone/safe/{appId}.json") public void getStandaloneByAppIdAndKey(HttpServletRequest request,@PathVariable long appId,Model model){
  if (!handleRedisApp(appId,request,model,ConstUtils.CACHE_REDIS_STANDALONE,true)) {
    return;
  }
  getRedisStandaloneInfo(request,appId,model);
}
