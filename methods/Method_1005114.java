/** 
 * Angular jeecgDEMO
 * @param pageNo
 * @param pageSize
 * @param entity
 * @param request
 * @param response
 * @param dataGrid
 * @return
 */
@RequestMapping(value="/list",method=RequestMethod.GET) @ResponseBody @ApiOperation(value="jeecgDemo????",produces="application/json",httpMethod="GET") public ResponseMessage<Map<String,Object>> list(@RequestParam("pageNo") int pageNo,@RequestParam("pageSize") int pageSize,JeecgDemoEntity entity,HttpServletRequest request,HttpServletResponse response,DataGrid dataGrid){
  InterfaceRuleDto interfaceRuleDto=InterfaceUtil.getInterfaceRuleDto(request,InterfaceEnum.jeecgdemo_list);
  if (interfaceRuleDto == null) {
    return Result.error("??????????");
  }
  CriteriaQuery query=new CriteriaQuery(JeecgDemoEntity.class,dataGrid);
  InterfaceUtil.installCriteriaQuery(query,interfaceRuleDto,InterfaceEnum.jeecgdemo_list);
  org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(query,entity,request.getParameterMap());
  query.setCurPage(pageNo <= 0 ? 1 : pageNo);
  query.setPageSize(pageSize);
  query.addOrder("createDate",SortDirection.desc);
  query.add();
  this.jeecgDemoService.getDataGridReturn(query,true);
  Map<String,Object> resultMap=new HashMap<String,Object>();
  resultMap.put("data",dataGrid.getResults());
  resultMap.put("total",dataGrid.getTotal());
  return Result.success(resultMap);
}
