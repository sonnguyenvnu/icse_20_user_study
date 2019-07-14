/** 
 * ??????????key
 * @param method
 * @param originKey
 * @param isTableKey
 * @param saveLogic ??????? & | !
 * @param verifyName ??key?????????/???
 * @return
 */
public static String getRealKey(RequestMethod method,String originKey,boolean isTableKey,boolean saveLogic,boolean verifyName,String quote) throws Exception {
  Log.i(TAG,"getRealKey  saveLogic = " + saveLogic + "; originKey = " + originKey);
  if (originKey == null || originKey.startsWith(quote) || zuo.biao.apijson.JSONObject.isArrayKey(originKey)) {
    Log.w(TAG,"getRealKey  originKey == null || originKey.startsWith(`)" + " || zuo.biao.apijson.JSONObject.isArrayKey(originKey) >>  return originKey;");
    return originKey;
  }
  String key=new String(originKey);
  if (key.endsWith("$")) {
    key=key.substring(0,key.length() - 1);
  }
 else   if (key.endsWith("~") || key.endsWith("?")) {
    key=key.substring(0,key.length() - 1);
    if (key.endsWith("*")) {
      key=key.substring(0,key.length() - 1);
    }
  }
 else   if (key.endsWith("%")) {
    key=key.substring(0,key.length() - 1);
  }
 else   if (key.endsWith("{}")) {
    key=key.substring(0,key.length() - 2);
  }
 else   if (key.endsWith("}{")) {
    key=key.substring(0,key.length() - 2);
  }
 else   if (key.endsWith("<>")) {
    key=key.substring(0,key.length() - 2);
  }
 else   if (key.endsWith("()")) {
    key=key.substring(0,key.length() - 2);
  }
 else   if (key.endsWith("@")) {
    key=key.substring(0,key.length() - 1);
  }
 else   if (key.endsWith(">=")) {
    key=key.substring(0,key.length() - 2);
  }
 else   if (key.endsWith("<=")) {
    key=key.substring(0,key.length() - 2);
  }
 else   if (key.endsWith(">")) {
    key=key.substring(0,key.length() - 1);
  }
 else   if (key.endsWith("<")) {
    key=key.substring(0,key.length() - 1);
  }
 else   if (key.endsWith("+")) {
    if (method == PUT) {
      key=key.substring(0,key.length() - 1);
    }
  }
 else   if (key.endsWith("-")) {
    if (method == PUT) {
      key=key.substring(0,key.length() - 1);
    }
  }
  String last=null;
  if (RequestMethod.isQueryMethod(method)) {
    last=key.isEmpty() ? "" : key.substring(key.length() - 1);
    if ("&".equals(last) || "|".equals(last) || "!".equals(last)) {
      key=key.substring(0,key.length() - 1);
    }
 else {
      last=null;
    }
  }
  if (isTableKey) {
    key=Pair.parseEntry(key,true).getKey();
  }
 else {
    key=Pair.parseEntry(key).getValue();
  }
  if (verifyName && StringUtil.isName(key.startsWith("@") ? key.substring(1) : key) == false) {
    throw new IllegalArgumentException(method + "????? " + originKey + " ????" + " key:value ??key????? '@key' ? 'key[???][???]' ? PUT???? 'key+' / 'key-' ?");
  }
  if (saveLogic && last != null) {
    key=key + last;
  }
  Log.i(TAG,"getRealKey  return key = " + key);
  return key;
}
