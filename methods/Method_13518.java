/** 
 * ???? Example
 * @param code
 * @return
 */
@RequestMapping("/sms-send.do") public SendSmsResponse sendCheckCode(@RequestParam(name="code") String code){
  SendSmsRequest request=new SendSmsRequest();
  request.setPhoneNumbers("******");
  request.setSignName("******");
  request.setTemplateCode("******");
  request.setTemplateParam("{\"code\":\"" + code + "\"}");
  request.setOutId("****TraceId");
  try {
    SendSmsResponse sendSmsResponse=smsService.sendSmsRequest(request);
    return sendSmsResponse;
  }
 catch (  ClientException e) {
    e.printStackTrace();
  }
  return new SendSmsResponse();
}
