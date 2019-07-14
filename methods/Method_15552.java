/** 
 * ??SET
 * @param method
 * @param content
 * @return
 * @throws Exception 
 */
@JSONField(serialize=false) public String getSetString(RequestMethod method,Map<String,Object> content,boolean verifyName) throws Exception {
  Set<String> set=content == null ? null : content.keySet();
  String setString="";
  if (set != null && set.size() > 0) {
    String quote=getQuote();
    boolean isFirst=true;
    int keyType=0;
    Object value;
    String idKey=getIdKey();
    for (    String key : set) {
      if (key == null || idKey.equals(key)) {
        continue;
      }
      if (key.endsWith("+")) {
        keyType=1;
      }
 else       if (key.endsWith("-")) {
        keyType=2;
      }
      value=content.get(key);
      key=getRealKey(method,key,false,true,verifyName,quote);
      setString+=(isFirst ? "" : ", ") + (getKey(key) + "=" + (keyType == 1 ? getAddString(key,value) : (keyType == 2 ? getRemoveString(key,value) : getValue(value))));
      isFirst=false;
    }
  }
  if (setString.isEmpty()) {
    throw new IllegalArgumentException("PUT ?????Table??????? key:value ?");
  }
  return " SET " + setString;
}
