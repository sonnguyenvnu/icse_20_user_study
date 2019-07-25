/** 
 * ????????
 * @return
 */
@RequestMapping(params="update") public ModelAndView update(TSDepart depart,HttpServletRequest req){
  List<TSDepart> departList=systemService.getList(TSDepart.class);
  req.setAttribute("departList",departList);
  if (StringUtil.isNotEmpty(depart.getId())) {
    depart=systemService.getEntity(TSDepart.class,depart.getId());
    req.setAttribute("depart",depart);
  }
  return new ModelAndView("system/depart/depart");
}
