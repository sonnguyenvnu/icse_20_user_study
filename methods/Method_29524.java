/** 
 * ????id?????Redis????
 * @return
 */
@RequestMapping(value="/appInstanceList") public ModelAndView appInstanceList(HttpServletRequest request,HttpServletResponse response,Model model){
  String appIdStr=request.getParameter("appId");
  long appId=NumberUtils.toLong(appIdStr);
  AppDesc appDesc=appService.getByAppId(appId);
  StringBuffer instances=new StringBuffer();
  List<InstanceInfo> instanceList=appService.getAppInstanceInfo(appId);
  if (CollectionUtils.isNotEmpty(instanceList)) {
    for (int i=0; i < instanceList.size(); i++) {
      InstanceInfo instanceInfo=instanceList.get(i);
      if (instanceInfo == null) {
        continue;
      }
      if (instanceInfo.isOffline()) {
        continue;
      }
      if (TypeUtil.isRedisSentinel(appDesc.getType())) {
        if (TypeUtil.isRedisSentinel(instanceInfo.getType())) {
          continue;
        }
        if (!redisCenter.isMaster(appId,instanceInfo.getIp(),instanceInfo.getPort())) {
          continue;
        }
      }
      instances.append(instanceInfo.getIp() + ":" + instanceInfo.getPort());
      if (i != instanceList.size() - 1) {
        instances.append(ConstUtils.NEXT_LINE);
      }
    }
  }
  model.addAttribute("instances",instances.toString());
  model.addAttribute("appType",appDesc == null ? -1 : appDesc.getType());
  return new ModelAndView("");
}
