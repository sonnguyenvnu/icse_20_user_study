/** 
 * ???
 * @param request
 * @return
 */
@RequestMapping(params="Schedule") public ModelAndView Schedule(HttpServletRequest request){
  return new ModelAndView("com/jeecg/demo/echartsDemo/Schedule");
}
