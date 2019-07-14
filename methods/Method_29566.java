/** 
 * ?????
 */
@RequestMapping(value="/monitor") public ModelAndView monitor(HttpServletRequest request,HttpServletResponse response,Model model){
  instanceAlertConfigService.monitorLastMinuteAllInstanceInfo();
  return null;
}
