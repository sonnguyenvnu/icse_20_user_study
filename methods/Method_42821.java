@RequestMapping(value="/ajaxPaymentReport",method={RequestMethod.POST,RequestMethod.GET}) @ResponseBody public List ajaxPaymentReport(HttpServletRequest request){
  RpUserInfo userInfo=(RpUserInfo)request.getSession().getAttribute(ConstantClass.USER);
  List<Map<String,String>> paymentReport=rpTradePaymentQueryService.getPaymentReport(userInfo.getUserNo());
  String jsonString=JSON.toJSONString(paymentReport);
  System.out.println(jsonString);
  return paymentReport;
}
