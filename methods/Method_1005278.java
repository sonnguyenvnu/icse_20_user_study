/** 
 * ????????
 * @return
 */
@RequestMapping(params="depart") public ModelAndView depart(){
  return new ModelAndView("system/organzation/departList");
}
