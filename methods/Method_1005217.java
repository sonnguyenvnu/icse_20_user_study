/** 
 * ????????
 * @param configId ??id-code
 * @param page ????
 * @param rows ????
 * @param request 
 * @param response
 */
@SuppressWarnings("unchecked") @RequestMapping(params="datagrid") public void datagrid(String configId,HttpServletRequest request,HttpServletResponse response){
  Map<String,Object> cgDynamGraphMap=null;
  try {
    cgDynamGraphMap=cgDynamGraphService.queryCgDynamGraphConfig(configId);
    if (cgDynamGraphMap.size() <= 0) {
      throw new CgReportNotFoundException("?????????!");
    }
  }
 catch (  Exception e) {
    throw new CgReportNotFoundException("??????????!" + e.getMessage());
  }
  Map configM=(Map)cgDynamGraphMap.get(CgReportConstant.MAIN);
  String querySql=(String)configM.get(CgReportConstant.CONFIG_SQL);
  List<Map<String,Object>> items=(List<Map<String,Object>>)cgDynamGraphMap.get(CgReportConstant.ITEMS);
  List<String> paramList=(List<String>)cgDynamGraphMap.get(CgReportConstant.PARAMS);
  Map pageSearchFields=new LinkedHashMap<String,Object>();
  Map<String,Object> paramData=new HashMap<String,Object>();
  if (paramList != null && paramList.size() > 0) {
    for (    String param : paramList) {
      String value=request.getParameter(param);
      value=value == null ? "" : value;
      querySql=querySql.replace("'${" + param + "}'",":" + param);
      querySql=querySql.replace("${" + param + "}",":" + param);
      paramData.put(param,value);
    }
  }
  for (  Map<String,Object> item : items) {
    String isQuery=(String)item.get(CgReportConstant.ITEM_ISQUERY);
    if (CgReportConstant.BOOL_TRUE.equalsIgnoreCase(isQuery)) {
      CgReportQueryParamUtil.loadQueryParams(request,item,pageSearchFields,paramData);
    }
  }
  String dbKey=(String)configM.get("db_source");
  List<Map<String,Object>> result=null;
  Long size=0l;
  if (StringUtils.isNotBlank(dbKey)) {
    Map map=null;
    if (paramData != null && paramData.size() > 0) {
      result=DynamicDBUtil.findListByHash(dbKey,SqlUtil.getFullSql(querySql,pageSearchFields),(HashMap<String,Object>)paramData);
      map=(Map)DynamicDBUtil.findOneByHash(dbKey,SqlUtil.getCountSql(querySql,pageSearchFields),(HashMap<String,Object>)paramData);
    }
 else {
      result=DynamicDBUtil.findList(dbKey,querySql);
      map=(Map)DynamicDBUtil.findOne(dbKey,SqlUtil.getCountSql(querySql,null));
    }
    if (map.get("COUNT(*)") instanceof BigDecimal) {
      BigDecimal count=(BigDecimal)map.get("COUNT(*)");
      size=count.longValue();
    }
 else {
      size=(Long)map.get("COUNT(*)");
    }
  }
 else {
    result=cgDynamGraphService.queryByCgDynamGraphSql(querySql,pageSearchFields,paramData);
    size=cgDynamGraphService.countQueryByCgDynamGraphSql(querySql,pageSearchFields,paramData);
  }
  cgReportService.dealDic(result,items);
  cgReportService.dealReplace(result,items);
  response.setContentType("application/json");
  response.setHeader("Cache-Control","no-store");
  PrintWriter writer=null;
  try {
    writer=response.getWriter();
    writer.println(CgReportQueryParamUtil.getJson(result,size));
    writer.flush();
  }
 catch (  IOException e) {
    e.printStackTrace();
  }
 finally {
    try {
      writer.close();
    }
 catch (    Exception e2) {
    }
  }
}
