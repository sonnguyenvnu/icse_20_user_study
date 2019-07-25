/** 
 * bootstrap-suggest-plugin demo
 * @param request
 * @return
 */
@RequestMapping(params="suggest") public ModelAndView suggest(HttpServletRequest request){
  return new ModelAndView("com/jeecg/demo/suggest");
}
