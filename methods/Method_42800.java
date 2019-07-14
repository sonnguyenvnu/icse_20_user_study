@RequestMapping("/authorize") @ResponseBody public String wxAuthorize(@RequestParam("code") String code){
  String authUrl=WeixinConfigUtil.xAuthUrl.replace("{APPID}",WeixinConfigUtil.xAppId).replace("{SECRET}",WeixinConfigUtil.xPartnerKey).replace("{JSCODE}",code).replace("{GRANTTYPE}",WeixinConfigUtil.xGrantType);
  try {
    HttpClient httpClient=new HttpClient();
    GetMethod getMethod=new GetMethod(authUrl);
    httpClient.executeMethod(getMethod);
    String result=getMethod.getResponseBodyAsString();
    logger.info("???code????:{}",result);
    return result;
  }
 catch (  IOException e) {
    logger.info("??openId??!");
    return null;
  }
}
