/** 
 * JeecgDemo ??????
 * @param jeecgDemo
 * @param req
 * @return
 */
@RequestMapping(params="print") public ModelAndView print(JeecgDemoEntity jeecgDemo,HttpServletRequest req){
  List<TSDepart> departList=systemService.getList(TSDepart.class);
  req.setAttribute("departList",departList);
  if (StringUtil.isNotEmpty(jeecgDemo.getId())) {
    jeecgDemo=jeecgDemoService.getEntity(JeecgDemoEntity.class,jeecgDemo.getId());
    req.setAttribute("jgDemo",jeecgDemo);
    if ("0".equals(jeecgDemo.getSex()))     req.setAttribute("sex","?");
    if ("1".equals(jeecgDemo.getSex()))     req.setAttribute("sex","?");
  }
  return new ModelAndView("com/jeecg/demo/jeecgDemo-print");
}
