/** 
 * ?????
 */
@RequestMapping(value="/init") public ModelAndView init(HttpServletRequest request,HttpServletResponse response,Model model){
  model.addAttribute("instanceAlertCheckCycleEnumList",InstanceAlertCheckCycleEnum.getInstanceAlertCheckCycleEnumList());
  model.addAttribute("instanceAlertCompareTypeEnumList",InstanceAlertCompareTypeEnum.getInstanceAlertCompareTypeEnumList());
  model.addAttribute("redisAlertConfigEnumList",RedisAlertConfigEnum.getRedisAlertConfigEnumList());
  model.addAttribute("instanceAlertAllList",instanceAlertConfigService.getByType(InstanceAlertTypeEnum.ALL_ALERT.getValue()));
  model.addAttribute("instanceAlertList",instanceAlertConfigService.getAll());
  model.addAttribute("success",request.getParameter("success"));
  model.addAttribute("instanceAlertValueActive",SuccessEnum.SUCCESS.value());
  List<InstanceAlertConfig> instanceAlertSpecialList=instanceAlertConfigService.getByType(InstanceAlertTypeEnum.INSTANCE_ALERT.getValue());
  fillinstanceHostPort(instanceAlertSpecialList);
  model.addAttribute("instanceAlertSpecialList",instanceAlertSpecialList);
  return new ModelAndView("manage/instanceAlert/init");
}
