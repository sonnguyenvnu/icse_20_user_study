public List<Map<String,String>> getPaymentReport(String merchantNo){
  Map<String,Object> paramMap=new HashMap<String,Object>();
  paramMap.put("status",TradeStatusEnum.SUCCESS.name());
  paramMap.put("merchantNo",merchantNo);
  return super.getSessionTemplate().selectList(getStatement("getPaymentReport"),paramMap);
}
