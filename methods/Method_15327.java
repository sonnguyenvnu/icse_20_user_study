/** 
 * ?????
 * @param request
 * @return
 */
@PostMapping("gets/verify") public JSONObject getVerify(@RequestBody String request){
  JSONObject requestObject=null;
  int type;
  String phone;
  try {
    requestObject=DemoParser.parseRequest(request);
    type=requestObject.getIntValue(TYPE);
    phone=requestObject.getString(PHONE);
  }
 catch (  Exception e) {
    return DemoParser.extendErrorResult(requestObject,e);
  }
  return new DemoParser(GETS,true).parseResponse(newVerifyRequest(type,phone,null));
}
