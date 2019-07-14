/** 
 * ????
 */
@RequestMapping(value="/preview") public ModelAndView preview(HttpServletRequest request,HttpServletResponse response,Model model){
  int type=NumberUtils.toInt(request.getParameter("type"),-1);
  String host=StringUtils.isBlank(request.getParameter("host")) ? "127.0.0.1" : request.getParameter("host");
  int port=NumberUtils.toInt(request.getParameter("port"),6379);
  int maxMemory=NumberUtils.toInt(request.getParameter("maxMemory"),2048);
  int sentinelPort=NumberUtils.toInt(request.getParameter("sentinelPort"),26379);
  String masterName=StringUtils.isBlank(request.getParameter("masterName")) ? "myMaster" : request.getParameter("masterName");
  int quorum=NumberUtils.toInt(request.getParameter("quorum"),2);
  List<String> configList=new ArrayList<String>();
  if (ConstUtils.CACHE_REDIS_STANDALONE == type) {
    configList=redisConfigTemplateService.handleCommonConfig(port,maxMemory);
  }
 else   if (ConstUtils.CACHE_REDIS_SENTINEL == type) {
    configList=redisConfigTemplateService.handleSentinelConfig(masterName,host,port,sentinelPort);
  }
 else   if (ConstUtils.CACHE_TYPE_REDIS_CLUSTER == type) {
    configList=redisConfigTemplateService.handleClusterConfig(port);
  }
  model.addAttribute("type",type);
  model.addAttribute("host",host);
  model.addAttribute("port",port);
  model.addAttribute("maxMemory",maxMemory);
  model.addAttribute("sentinelPort",sentinelPort);
  model.addAttribute("masterName",masterName);
  model.addAttribute("configList",configList);
  return new ModelAndView("manage/redisConfig/preview");
}
