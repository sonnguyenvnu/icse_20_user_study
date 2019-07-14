/** 
 * ??appId??RedisStandalone????
 * @param appId
 */
@RequestMapping(value="/redis/standalone/{appId}.json") public void getStandaloneAppById(HttpServletRequest request,@PathVariable long appId,Model model){
  if (!handleRedisApp(appId,request,model,ConstUtils.CACHE_REDIS_STANDALONE,false)) {
    return;
  }
  getRedisStandaloneInfo(request,appId,model);
}
