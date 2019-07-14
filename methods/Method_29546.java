/** 
 * ????slot??master??
 * @param appId
 * @param sentinelHost
 */
@RequestMapping(value="/addFailSlotsMaster") public void addFailSlotsMaster(HttpServletRequest request,HttpServletResponse response,Model model,long appId,String failSlotsMasterHost,int instanceId){
  AppUser appUser=getUserInfo(request);
  logger.warn("user {} addFailSlotsMaster: appId:{}, instanceId {}, newMasterHost:{}",appUser.getName(),appId,instanceId,failSlotsMasterHost);
  RedisOperateEnum redisOperateEnum=RedisOperateEnum.FAIL;
  if (appId > 0 && StringUtils.isNotBlank(failSlotsMasterHost)) {
    try {
      redisOperateEnum=redisDeployCenter.addSlotsFailMaster(appId,instanceId,failSlotsMasterHost);
    }
 catch (    Exception e) {
      logger.error(e.getMessage(),e);
    }
  }
  logger.warn("user {} addFailSlotsMaster: appId:{}, instanceId {}, newMasterHost:{} result is {}",appUser.getName(),appId,instanceId,failSlotsMasterHost,redisOperateEnum.getValue());
  write(response,String.valueOf(redisOperateEnum.getValue()));
}
