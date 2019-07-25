@Override public String login(String user,String password){
  String response=transport.execute(SimpleRequestBuilder.buildNewJsonRequest(ClassKeys.SYSTEM).addFunction(FunctionKeys.LOGIN).addParameter(ParameterKeys.USER,user).addParameter(ParameterKeys.PASSWORD,password).buildRequestString());
  JsonObject responseObj=JSONResponseHandler.toJsonObject(response);
  if (JSONResponseHandler.checkResponse(responseObj)) {
    JsonObject obj=JSONResponseHandler.getResultJsonObject(responseObj);
    String tokenStr=null;
    if (checkBlankField(obj,JSONApiResponseKeysEnum.TOKEN.getKey())) {
      tokenStr=obj.get(JSONApiResponseKeysEnum.TOKEN.getKey()).getAsString();
    }
    if (tokenStr != null) {
      return tokenStr;
    }
  }
  return null;
}
