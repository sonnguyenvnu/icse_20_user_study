@RequestMapping(value="/ajaxSettList",method={RequestMethod.POST,RequestMethod.GET}) @ResponseBody public String ajaxPaymentList(HttpServletRequest request,@RequestBody JSONParam[] params) throws IllegalAccessException, InvocationTargetException {
  HashMap<String,String> paramMap=convertToMap(params);
  String sEcho=paramMap.get("sEcho");
  int start=Integer.parseInt(paramMap.get("iDisplayStart"));
  int length=Integer.parseInt(paramMap.get("iDisplayLength"));
  String beginDate=paramMap.get("beginDate");
  String endDate=paramMap.get("endDate");
  if (StringUtil.isEmpty(beginDate) && !StringUtil.isEmpty(endDate)) {
    beginDate=endDate;
  }
  if (StringUtil.isEmpty(endDate) && !StringUtil.isEmpty(beginDate)) {
    endDate=beginDate;
  }
  String merchantRequestNo=paramMap.get("merchantRequestNo");
  String status=paramMap.get("status");
  RpUserInfo userInfo=(RpUserInfo)request.getSession().getAttribute(ConstantClass.USER);
  PageParam pageParam=new PageParam(start / length + 1,length);
  Map<String,Object> settMap=new HashMap<String,Object>();
  settMap.put("userNo",userInfo.getUserNo());
  settMap.put("settStatus",status);
  settMap.put("merchantRequestNo",merchantRequestNo);
  settMap.put("beginDate",beginDate);
  settMap.put("endDate",endDate);
  PageBean pageBean=rpSettQueryService.querySettRecordListPage(pageParam,settMap);
  Long count=Long.valueOf(pageBean.getTotalCount() + "");
  String jsonString=JSON.toJSONString(pageBean.getRecordList());
  String json="{\"sEcho\":" + sEcho + ",\"iTotalRecords\":" + count.longValue() + ",\"iTotalDisplayRecords\":" + count.longValue() + ",\"aaData\":" + jsonString + "}";
  return json;
}
