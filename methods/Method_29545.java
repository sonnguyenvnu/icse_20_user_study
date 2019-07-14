/** 
 * ??sentinel??
 * @param appId
 * @param sentinelHost
 * @return
 */
@RequestMapping(value="/addSentinel") public void addSentinel(HttpServletRequest request,HttpServletResponse response,Model model,long appId,String sentinelHost){
  AppUser appUser=getUserInfo(request);
  logger.warn("user {} addSentinel: appId:{}, sentinelHost:{}",appUser.getName(),appId,sentinelHost);
  boolean success=false;
  if (appId > 0 && StringUtils.isNotBlank(sentinelHost)) {
    try {
      success=redisDeployCenter.addSentinel(appId,sentinelHost);
    }
 catch (    Exception e) {
      logger.error(e.getMessage(),e);
    }
  }
  logger.warn("user {} addSentinel: appId:{}, sentinelHost:{} result is {}",appUser.getName(),appId,sentinelHost,success);
  write(response,String.valueOf(success == true ? SuccessEnum.SUCCESS.value() : SuccessEnum.FAIL.value()));
}
