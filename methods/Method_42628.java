public static Map<String,Object> singleTradeQuery(String outTradeNo){
  SortedMap<String,String> paramMap=new TreeMap<>();
  paramMap.put("service","single_trade_query");
  paramMap.put("partner",AlipayConfigUtil.partner);
  paramMap.put("_input_charset",AlipayConfigUtil.input_charset);
  paramMap.put("out_trade_no",outTradeNo);
  paramMap.put("sign",getSign(paramMap,AlipayConfigUtil.key));
  paramMap.put("sign_type",AlipayConfigUtil.sign_type);
  HttpProtocolHandler httpProtocolHandler=HttpProtocolHandler.getInstance();
  HttpRequest request=new HttpRequest(HttpResultType.BYTES);
  request.setCharset(AlipayConfigUtil.input_charset);
  request.setParameters(generatNameValuePair(paramMap));
  request.setUrl("https://mapi.alipay.com/gateway.do?_input_charset=" + AlipayConfigUtil.input_charset);
  String strResult=null;
  try {
    HttpResponse response=httpProtocolHandler.execute(request,"","");
    if (response == null) {
      return null;
    }
    strResult=response.getStringResult();
  }
 catch (  Exception e) {
    logger.info("???????--?????");
  }
  logger.info("???????--????:{}",strResult);
  try {
    Document document=DocumentHelper.parseText(strResult);
    List<Element> tradeList=document.getRootElement().element("response").element("trade").elements();
    SortedMap<String,String> responseMap=new TreeMap<>();
    for (    Element ele : tradeList) {
      responseMap.put(ele.getName(),ele.getText());
    }
    String resultSign=getSign(responseMap,AlipayConfigUtil.key);
    String sign=document.getRootElement().element("sign").getText();
    if (resultSign.equals(sign)) {
      Map<String,Object> resultMap=new HashMap<>();
      resultMap.putAll(responseMap);
      resultMap.put("is_success",document.getRootElement().element("is_success").getText());
      return resultMap;
    }
 else {
      logger.info("???--?????????:{},????:[{}],??????:[{}]",sign,resultSign);
      return null;
    }
  }
 catch (  DocumentException e) {
    logger.info("???????--?????????");
    return null;
  }
}
