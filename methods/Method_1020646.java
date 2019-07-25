/** 
 * ????
 * @param text ??
 */
public boolean process(String text){
  String webhook=dingTalkPropertiesConfig.getWebhook();
  if (StringUtils.isBlank(webhook)) {
    log.error("???????webhook??");
    return false;
  }
  DingTalkMsgTemplate dingTalkMsgTemplate=new DingTalkMsgTemplate();
  dingTalkMsgTemplate.setMsgtype("text");
  DingTalkMsgTemplate.TextBean textBean=new DingTalkMsgTemplate.TextBean();
  textBean.setContent(text);
  dingTalkMsgTemplate.setText(textBean);
  HttpFeedback result=null;
  try {
    result=HttpContacter.p().doPost(webhook,JSONObject.toJSONString(dingTalkMsgTemplate));
  }
 catch (  Exception e) {
    log.error("??????:{}",e.getMessage());
  }
  log.info("??????,????:{}",result.getReceiptStr());
  return true;
}
