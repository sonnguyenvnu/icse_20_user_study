/** 
 * ????
 * @return
 */
@RequestMapping(value="/start") public ModelAndView start(HttpServletRequest request,HttpServletResponse response,Model model){
  String migrateMachineIp=request.getParameter("migrateMachineIp");
  String sourceRedisMigrateIndex=request.getParameter("sourceRedisMigrateIndex");
  AppDataMigrateEnum sourceRedisMigrateEnum=AppDataMigrateEnum.getByIndex(NumberUtils.toInt(sourceRedisMigrateIndex,-1));
  String sourceServers=request.getParameter("sourceServers");
  String targetRedisMigrateIndex=request.getParameter("targetRedisMigrateIndex");
  AppDataMigrateEnum targetRedisMigrateEnum=AppDataMigrateEnum.getByIndex(NumberUtils.toInt(targetRedisMigrateIndex,-1));
  String targetServers=request.getParameter("targetServers");
  long sourceAppId=NumberUtils.toLong(request.getParameter("sourceAppId"));
  long targetAppId=NumberUtils.toLong(request.getParameter("targetAppId"));
  String redisSourcePass=request.getParameter("redisSourcePass");
  String redisTargetPass=request.getParameter("redisTargetPass");
  AppUser appUser=getUserInfo(request);
  long userId=appUser == null ? 0 : appUser.getId();
  boolean isSuccess=appDataMigrateCenter.migrate(migrateMachineIp,sourceRedisMigrateEnum,sourceServers,targetRedisMigrateEnum,targetServers,sourceAppId,targetAppId,redisSourcePass,redisTargetPass,userId);
  model.addAttribute("status",isSuccess ? 1 : 0);
  return new ModelAndView("");
}
