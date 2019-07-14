public String toJson(){
  JSONObject jsonObject=new JSONObject();
  try {
    jsonObject.put(CALLBACK_ID_STR,getCallbackId());
    jsonObject.put(DATA_STR,getData());
    jsonObject.put(HANDLER_NAME_STR,getHandlerName());
    String data=getResponseData();
    if (TextUtils.isEmpty(data)) {
      jsonObject.put(RESPONSE_DATA_STR,data);
    }
 else {
      jsonObject.put(RESPONSE_DATA_STR,new JSONTokener(data).nextValue());
    }
    jsonObject.put(RESPONSE_DATA_STR,getResponseData());
    jsonObject.put(RESPONSE_ID_STR,getResponseId());
    return jsonObject.toString();
  }
 catch (  JSONException e) {
    e.printStackTrace();
  }
  return null;
}
