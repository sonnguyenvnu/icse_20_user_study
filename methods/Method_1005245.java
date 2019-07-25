/** 
 * ?????? ????
 * @return
 */
@RequestMapping(params="list") public ModelAndView list(HttpServletRequest request){
  return new ModelAndView("jeecg/superquery/superQueryMainList");
}
