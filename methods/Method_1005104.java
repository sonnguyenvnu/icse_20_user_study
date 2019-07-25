/** 
 * ?????tab demo???????
 * @param request
 * @return
 */
@RequestMapping(params="ueditor") public ModelAndView ueditor(HttpServletRequest request){
  logger.info("ueditor");
  return new ModelAndView("com/jeecg/demo/ueditor");
}
