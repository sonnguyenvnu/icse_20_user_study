private static String getRawString(String name,JSONObject json){
  try {
    if (json.isNull(name)) {
      return null;
    }
 else {
      return json.getString(name);
    }
  }
 catch (  JSONException jsone) {
    return null;
  }
}
