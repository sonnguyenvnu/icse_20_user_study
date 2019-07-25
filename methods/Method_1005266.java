/** 
 * ????????
 * @return
 */
@RequestMapping(params="addorupdate") public ModelAndView addorupdate(TSFunction function,HttpServletRequest req){
  String functionid=req.getParameter("id");
  List<TSFunction> fuinctionlist=systemService.getList(TSFunction.class);
  req.setAttribute("flist",fuinctionlist);
  List<TSIcon> iconlist=systemService.findByQueryString("from TSIcon where iconType != 3");
  req.setAttribute("iconlist",iconlist);
  List<TSIcon> iconDeskList=systemService.findByQueryString("from TSIcon where iconType = 3");
  req.setAttribute("iconDeskList",iconDeskList);
  if (functionid != null) {
    function=systemService.getEntity(TSFunction.class,functionid);
    req.setAttribute("function",function);
  }
  if (function.getTSFunction() != null && function.getTSFunction().getId() != null) {
    function.setFunctionLevel((short)1);
    function.setTSFunction((TSFunction)systemService.getEntity(TSFunction.class,function.getTSFunction().getId()));
    req.setAttribute("function",function);
  }
  return new ModelAndView("system/function/function");
}
