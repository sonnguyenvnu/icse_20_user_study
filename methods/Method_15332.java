/** 
 * ????
 * @param request ??String???encode??decode
 * @return
 * @see <pre> ??????? { "oldPassword": 123456, "Privacy":{ "id": 13000082001, "_password": "1234567" } } ??????+????? { "verify": "1234", "Privacy":{ "phone": "13000082001", "_password": "1234567" } } </pre>
 */
@PostMapping("put/password") public JSONObject putPassword(@RequestBody String request){
  JSONObject requestObject=null;
  String oldPassword;
  String verify;
  int type=Verify.TYPE_PASSWORD;
  JSONObject privacyObj;
  long userId;
  String phone;
  String password;
  try {
    requestObject=DemoParser.parseRequest(request);
    oldPassword=StringUtil.getString(requestObject.getString(OLD_PASSWORD));
    verify=StringUtil.getString(requestObject.getString(VERIFY));
    requestObject.remove(OLD_PASSWORD);
    requestObject.remove(VERIFY);
    privacyObj=requestObject.getJSONObject(PRIVACY_);
    if (privacyObj == null) {
      throw new IllegalArgumentException(PRIVACY_ + " ?????");
    }
    userId=privacyObj.getLongValue(ID);
    phone=privacyObj.getString(PHONE);
    password=privacyObj.getString(_PASSWORD);
    if (StringUtil.isEmpty(password,true)) {
      type=Verify.TYPE_PAY_PASSWORD;
      password=privacyObj.getString(_PAY_PASSWORD);
      if (StringUtil.isNumberPassword(password) == false) {
        throw new IllegalArgumentException(PRIVACY_ + "/" + _PAY_PASSWORD + ":value ?value????");
      }
    }
 else {
      if (StringUtil.isPassword(password) == false) {
        throw new IllegalArgumentException(PRIVACY_ + "/" + _PASSWORD + ":value ?value????");
      }
    }
  }
 catch (  Exception e) {
    return DemoParser.extendErrorResult(requestObject,e);
  }
  if (StringUtil.isPassword(oldPassword)) {
    if (userId <= 0) {
      return DemoParser.extendErrorResult(requestObject,new IllegalArgumentException(ID + ":value ?value????"));
    }
    if (oldPassword.equals(password)) {
      return DemoParser.extendErrorResult(requestObject,new ConflictException("?????????"));
    }
    Privacy privacy=new Privacy(userId);
    if (type == Verify.TYPE_PASSWORD) {
      privacy.setPassword(oldPassword);
    }
 else {
      privacy.setPayPassword(oldPassword);
    }
    JSONResponse response=new JSONResponse(new DemoParser(HEAD,true).parseResponse(new JSONRequest(privacy).setFormat(true)));
    if (JSONResponse.isExist(response.getJSONResponse(PRIVACY_)) == false) {
      return DemoParser.extendErrorResult(requestObject,new ConditionErrorException("???????????????"));
    }
  }
 else   if (StringUtil.isPhone(phone) && StringUtil.isVerify(verify)) {
    JSONResponse response=new JSONResponse(headVerify(type,phone,verify));
    if (JSONResponse.isSuccess(response) == false) {
      return response;
    }
    if (JSONResponse.isExist(response.getJSONResponse(VERIFY_)) == false) {
      return DemoParser.extendErrorResult(response,new ConditionErrorException("??????????"));
    }
    response=new JSONResponse(new DemoParser(GET,true).parseResponse(new JSONRequest(new Privacy().setPhone(phone))));
    Privacy privacy=response.getObject(Privacy.class);
    long id=privacy == null ? 0 : BaseModel.value(privacy.getId());
    privacyObj.remove(PHONE);
    privacyObj.put(ID,id);
    requestObject.put(PRIVACY_,privacyObj);
  }
 else {
    return DemoParser.extendErrorResult(requestObject,new IllegalArgumentException("?????? ??? ? ???+??? ?"));
  }
  requestObject.put(JSONRequest.KEY_FORMAT,true);
  return new DemoParser(PUT,true).parseResponse(requestObject);
}
