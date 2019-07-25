/** 
 * ????????
 */
@RequestMapping(params="addorupdaterule") public ModelAndView addorupdaterule(TSInterfaceDdataRuleEntity operation,HttpServletRequest req){
  if (operation.getId() != null) {
    operation=systemService.getEntity(TSInterfaceDdataRuleEntity.class,operation.getId());
    req.setAttribute("operation",operation);
  }
  String interfaceId=oConvertUtils.getString(req.getParameter("interfaceId"));
  req.setAttribute("interfaceId",interfaceId);
  return new ModelAndView("system/tsinterface/ruleData");
}
