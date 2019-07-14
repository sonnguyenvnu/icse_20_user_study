/** 
 * sentinelFailOver??
 * @param appId
 * @return
 */
@RequestMapping("/sentinelFailOver") public void sentinelFailOver(HttpServletRequest request,HttpServletResponse response,Model model,long appId){
  AppUser appUser=getUserInfo(request);
  logger.warn("user {} sentinelFailOver, appId:{}",appUser.getName(),appId);
  boolean success=false;
  if (appId > 0) {
    try {
      success=redisDeployCenter.sentinelFailover(appId);
    }
 catch (    Exception e) {
      logger.error(e.getMessage(),e);
    }
  }
 else {
    logger.error("error param, sentinelFailOver: appId:{}",appId);
  }
  logger.warn("user {} sentinelFailOver, appId:{}, result is {}",appUser.getName(),appId,success);
  write(response,String.valueOf(success == true ? SuccessEnum.SUCCESS.value() : SuccessEnum.FAIL.value()));
}
