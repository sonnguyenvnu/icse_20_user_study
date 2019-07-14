@RequestMapping("/result/{payWayCode}") public String result(@PathVariable("payWayCode") String payWayCode,HttpServletRequest httpServletRequest,Model model) throws Exception {
  Map<String,String> resultMap=new HashMap<String,String>();
  Map requestParams=httpServletRequest.getParameterMap();
  for (Iterator iter=requestParams.keySet().iterator(); iter.hasNext(); ) {
    String name=(String)iter.next();
    String[] values=(String[])requestParams.get(name);
    String valueStr="";
    for (int i=0; i < values.length; i++) {
      valueStr=(i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
    }
    valueStr=new String(valueStr);
    resultMap.put(name,valueStr);
  }
  OrderPayResultVo scanPayByResult=rpTradePaymentManagerService.completeScanPayByResult(payWayCode,resultMap);
  model.addAttribute("scanPayByResult",scanPayByResult);
  return "PayResult";
}
