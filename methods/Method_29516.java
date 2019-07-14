/** 
 * ????
 * @return
 */
@RequestMapping(value="/check") public ModelAndView check(HttpServletRequest request,HttpServletResponse response,Model model){
  String migrateMachineIp=request.getParameter("migrateMachineIp");
  String sourceRedisMigrateIndex=request.getParameter("sourceRedisMigrateIndex");
  AppDataMigrateEnum sourceRedisMigrateEnum=AppDataMigrateEnum.getByIndex(NumberUtils.toInt(sourceRedisMigrateIndex,-1));
  String sourceServers=request.getParameter("sourceServers");
  String targetRedisMigrateIndex=request.getParameter("targetRedisMigrateIndex");
  AppDataMigrateEnum targetRedisMigrateEnum=AppDataMigrateEnum.getByIndex(NumberUtils.toInt(targetRedisMigrateIndex,-1));
  String targetServers=request.getParameter("targetServers");
  String redisSourcePass=request.getParameter("redisSourcePass");
  String redisTargetPass=request.getParameter("redisTargetPass");
  AppDataMigrateResult redisMigrateResult=appDataMigrateCenter.check(migrateMachineIp,sourceRedisMigrateEnum,sourceServers,targetRedisMigrateEnum,targetServers,redisSourcePass,redisTargetPass);
  model.addAttribute("status",redisMigrateResult.getStatus());
  model.addAttribute("message",redisMigrateResult.getMessage());
  return new ModelAndView("");
}
