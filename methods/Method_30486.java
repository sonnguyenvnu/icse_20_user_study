private void parseResponse(){
  try {
    bodyJson=new JSONObject(bodyString);
    code=bodyJson.optInt(Error.CODE,0);
    message=bodyJson.optString(Error.MSG,null);
    request=bodyJson.optString(Error.REQUEST,null);
    localizedMessage=bodyJson.optString(Error.LOCALIZED_MESSAGE,null);
  }
 catch (  JSONException e) {
    e.printStackTrace();
    code=Custom.INVALID_ERROR_RESPONSE;
  }
}
