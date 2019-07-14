/** 
 * ??????
 * @param appId
 */
@RequestMapping("/instance") public ModelAndView appInstance(HttpServletRequest request,HttpServletResponse response,Model model,Long appId){
  if (appId != null && appId > 0) {
    AppDesc appDesc=appService.getByAppId(appId);
    model.addAttribute("appDesc",appDesc);
    fillAppInstanceStats(appId,model);
    if (TypeUtil.isRedisCluster(appDesc.getType())) {
      Map<String,String> lossSlotsSegmentMap=redisCenter.getClusterLossSlots(appId);
      model.addAttribute("lossSlotsSegmentMap",lossSlotsSegmentMap);
    }
  }
  return new ModelAndView("manage/appOps/appInstance");
}
