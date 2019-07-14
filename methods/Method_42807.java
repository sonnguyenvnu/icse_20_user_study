@RequestMapping(value="/ajaxAccountInfo",method={RequestMethod.POST,RequestMethod.GET}) @ResponseBody public String ajaxAccountInfo(HttpServletRequest request,@RequestBody JSONParam[] params) throws IllegalAccessException, InvocationTargetException {
  RpUserInfo rpUserInfo=(RpUserInfo)request.getSession().getAttribute(ConstantClass.USER);
  String userNo=rpUserInfo.getUserNo();
  HashMap<String,String> paramMap=convertToMap(params);
  String sEcho=paramMap.get("sEcho");
  int start=Integer.parseInt(paramMap.get("iDisplayStart"));
  int length=Integer.parseInt(paramMap.get("iDisplayLength"));
  PageParam pageParam=new PageParam(start / length + 1,length);
  RpAccountHistory rpAccountHistory=new RpAccountHistory();
  rpAccountHistory.setUserNo(userNo);
  PageBean pageBean=rpAccountHistoryService.listPage(pageParam,rpAccountHistory);
  Long count=Long.valueOf(pageBean.getTotalCount() + "");
  String jsonString=JSON.toJSONString(pageBean.getRecordList());
  String json="{\"sEcho\":" + sEcho + ",\"iTotalRecords\":" + count.longValue() + ",\"iTotalDisplayRecords\":" + count.longValue() + ",\"aaData\":" + jsonString + "}";
  return json;
}
