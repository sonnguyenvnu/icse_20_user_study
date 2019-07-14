public static JSONObject parseObject(String text){
  Object obj=parse(text);
  if (obj instanceof JSONObject) {
    return (JSONObject)obj;
  }
  try {
    return (JSONObject)JSON.toJSON(obj);
  }
 catch (  RuntimeException e) {
    throw new JSONException("can not cast to JSONObject.",e);
  }
}
