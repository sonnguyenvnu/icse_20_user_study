/** 
 * ?????
 */
@RequestMapping(value="/init") public ModelAndView init(HttpServletRequest request,HttpServletResponse response,Model model){
  int type=NumberUtils.toInt(request.getParameter("type"),ConstUtils.CACHE_REDIS_STANDALONE);
  model.addAttribute("redisConfigList",redisConfigTemplateService.getByType(type));
  model.addAttribute("success",request.getParameter("success"));
  model.addAttribute("redisConfigActive",SuccessEnum.SUCCESS.value());
  model.addAttribute("type",type);
  return new ModelAndView("manage/redisConfig/init");
}
