/** 
 * jeecg_demo?? ????
 * @return
 */
@RequestMapping(params="list") public ModelAndView list(HttpServletRequest request){
  return new ModelAndView("com/jeecg/demo/jeecgDemoList");
}
