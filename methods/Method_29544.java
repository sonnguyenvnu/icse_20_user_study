/** 
 * redisCluster???failover
 * @param appId ??id
 * @param slaveInstanceId ???instanceId
 * @return
 */
@RequestMapping("/clusterSlaveFailOver") public void clusterSlaveFailOver(HttpServletRequest request,HttpServletResponse response,Model model,Long appId,int slaveInstanceId){
  boolean success=false;
  String failoverParam=request.getParameter("failoverParam");
  logger.warn("clusterSlaveFailOver: appId:{}, slaveInstanceId:{}, failoverParam:{}",appId,slaveInstanceId,failoverParam);
  if (appId != null && appId > 0 && slaveInstanceId > 0) {
    try {
      success=redisDeployCenter.clusterFailover(appId,slaveInstanceId,failoverParam);
    }
 catch (    Exception e) {
      logger.error(e.getMessage(),e);
    }
  }
 else {
    logger.error("error param clusterSlaveFailOver: appId:{}, slaveInstanceId:{}, failoverParam:{}",appId,slaveInstanceId,failoverParam);
  }
  logger.warn("clusterSlaveFailOver: appId:{}, slaveInstanceId:{}, failoverParam:{}, result is {}",appId,slaveInstanceId,failoverParam,success);
  write(response,String.valueOf(success == true ? SuccessEnum.SUCCESS.value() : SuccessEnum.FAIL.value()));
}
