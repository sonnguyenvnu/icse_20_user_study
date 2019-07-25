@Override public JsonObject query(String sessionToken,String query){
  String response=transport.execute(SimpleRequestBuilder.buildNewJsonRequest(ClassKeys.PROPERTY_TREE).addFunction(FunctionKeys.QUERY).addParameter(ParameterKeys.QUERY,query).addParameter(ParameterKeys.TOKEN,sessionToken).buildRequestString());
  JsonObject responseObj=JSONResponseHandler.toJsonObject(response);
  if (JSONResponseHandler.checkResponse(responseObj)) {
    return JSONResponseHandler.getResultJsonObject(responseObj);
  }
  return null;
}
