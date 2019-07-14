@Override public boolean sendMail(String title,String content,List<String> emailList,List<String> ccList){
  String alertUrl=ConstUtils.EMAIL_ALERT_INTERFACE;
  if (StringUtils.isBlank(alertUrl)) {
    logger.error("emailAlertInterface url is empty!");
    return false;
  }
  try {
    String charSet="UTF-8";
    Map<String,String> postMap=new HashMap<String,String>();
    postMap.put("title",title);
    postMap.put("content",content);
    postMap.put("receiver",StringUtils.join(emailList,","));
    if (ccList != null && ccList.size() > 0) {
      postMap.put("cc",StringUtils.join(ccList,","));
    }
    String responseStr=HttpRequestUtil.doPost(alertUrl,postMap,charSet);
    if (responseStr == null) {
      logger.error("?????? : url:{}",alertUrl);
    }
    return true;
  }
 catch (  Exception e) {
    logger.error(e.getMessage(),e);
    return false;
  }
}
