/** 
 * ???? Example
 * @param code
 * @return
 */
@RequestMapping("/batch-sms-send.do") public SendBatchSmsResponse batchsendCheckCode(@RequestParam(name="code") String code){
  SendBatchSmsRequest request=new SendBatchSmsRequest();
  request.setMethod(MethodType.GET);
  request.setPhoneNumberJson("[\"177********\",\"130********\"]");
  request.setSignNameJson("[\"*******\",\"*******\"]");
  request.setTemplateCode("******");
  request.setTemplateParamJson("[{\"code\":\"" + code + "\"},{\"code\":\"" + code + "\"}]");
  try {
    SendBatchSmsResponse sendSmsResponse=smsService.sendSmsBatchRequest(request);
    return sendSmsResponse;
  }
 catch (  ClientException e) {
    e.printStackTrace();
  }
  return new SendBatchSmsResponse();
}
