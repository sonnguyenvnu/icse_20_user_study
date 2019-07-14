static String getUnescapedString(String str,JSONObject json){
  return HTMLEntity.unescape(getRawString(str,json));
}
