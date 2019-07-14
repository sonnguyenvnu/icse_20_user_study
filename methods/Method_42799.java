@RequestMapping("/order/query") @ResponseBody public String orderQuery(String trxNo){
  return JSONObject.toJSONString(queryService.getRecordByTrxNo(trxNo));
}
