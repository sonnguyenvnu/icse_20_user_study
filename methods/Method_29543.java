/** 
 * redisCluster????: forget + shutdown
 * @param appId ??id
 * @param forgetInstanceId ???forget???
 * @return
 */
@RequestMapping("/clusterDelNode") public ModelAndView clusterDelNode(HttpServletRequest request,HttpServletResponse response,Model model,Long appId,int delNodeInstanceId){
  AppUser appUser=getUserInfo(request);
  logger.warn("user {}, clusterForget: appId:{}, instanceId:{}",appUser.getName(),appId,delNodeInstanceId);
  ClusterOperateResult checkClusterForgetResult=null;
  try {
    checkClusterForgetResult=redisDeployCenter.checkClusterForget(appId,delNodeInstanceId);
  }
 catch (  Exception e) {
    logger.error(e.getMessage(),e);
  }
  if (checkClusterForgetResult == null || !checkClusterForgetResult.isSuccess()) {
    model.addAttribute("success",checkClusterForgetResult.getStatus());
    model.addAttribute("message",checkClusterForgetResult.getMessage());
    return new ModelAndView("");
  }
  ClusterOperateResult delNodeResult=null;
  try {
    delNodeResult=redisDeployCenter.delNode(appId,delNodeInstanceId);
  }
 catch (  Exception e) {
    logger.error(e.getMessage(),e);
  }
  model.addAttribute("success",delNodeResult.getStatus());
  model.addAttribute("message",delNodeResult.getMessage());
  logger.warn("user {}, clusterForget: appId:{}, instanceId:{}, result is {}",appUser.getName(),appId,delNodeInstanceId,delNodeResult.getStatus());
  return new ModelAndView("");
}
