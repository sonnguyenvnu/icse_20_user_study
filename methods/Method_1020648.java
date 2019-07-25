/** 
 * ????
 * @param mobileMsgTemplate ??
 */
@Override public boolean process(MobileMsgTemplate mobileMsgTemplate){
  System.setProperty("sun.net.client.defaultConnectTimeout","10000");
  System.setProperty("sun.net.client.defaultReadTimeout","10000");
  IClientProfile profile=DefaultProfile.getProfile("cn-hangzhou",smsAliyunPropertiesConfig.getAccessKey(),smsAliyunPropertiesConfig.getSecretKey());
  try {
    DefaultProfile.addEndpoint("cn-hou","cn-hangzhou",PRODUCT,DOMAIN);
  }
 catch (  ClientException e) {
    log.error("???SDK ??",e);
    e.printStackTrace();
  }
  IAcsClient acsClient=new DefaultAcsClient(profile);
  SendSmsRequest request=new SendSmsRequest();
  request.setPhoneNumbers(mobileMsgTemplate.getMobile());
  request.setSignName(mobileMsgTemplate.getSignName());
  request.setTemplateCode(smsAliyunPropertiesConfig.getChannels().get(mobileMsgTemplate.getTemplate()));
  request.setTemplateParam(mobileMsgTemplate.getContext());
  request.setOutId(mobileMsgTemplate.getMobile());
  try {
    SendSmsResponse sendSmsResponse=acsClient.getAcsResponse(request);
    log.info("???????????{}??????{}",mobileMsgTemplate.getMobile(),sendSmsResponse.getCode());
  }
 catch (  ClientException e) {
    log.error("????");
    e.printStackTrace();
  }
  return true;
}
