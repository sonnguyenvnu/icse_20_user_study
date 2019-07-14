/** 
 * @param method
 * @param name
 * @param key
 * @param robj
 * @param idKey
 * @param atLeastOne ???????null
 */
private static void verifyId(@NotNull String method,@NotNull String name,@NotNull String key,@NotNull JSONObject robj,@NotNull String idKey,final int maxUpdateCount,boolean atLeastOne){
  Object id=robj.get(idKey);
  if (id != null && id instanceof Number == false && id instanceof String == false) {
    throw new IllegalArgumentException(method + "???" + name + "/" + key + " ??? " + idKey + ":value ?value?????? Long ? String ?");
  }
  String idInKey=idKey + "{}";
  JSONArray idIn=null;
  try {
    idIn=robj.getJSONArray(idInKey);
  }
 catch (  Exception e) {
    throw new IllegalArgumentException(method + "???" + name + "/" + key + " ??? " + idInKey + ":value ?value?????? [Long] ?");
  }
  if (idIn == null) {
    if (atLeastOne && id == null) {
      throw new IllegalArgumentException(method + "???" + name + "/" + key + " ?? " + idKey + " ? " + idInKey + " ????????");
    }
  }
 else {
    if (idIn.size() > maxUpdateCount) {
      throw new IllegalArgumentException(method + "???" + name + "/" + key + " ??? " + idInKey + ":[] ?[]??????? " + maxUpdateCount + " ?");
    }
    for (int i=0; i < idIn.size(); i++) {
      Object o=idIn.get(i);
      if (o != null && o instanceof Number == false && o instanceof String == false) {
        throw new IllegalArgumentException(method + "???" + name + "/" + key + " ??? " + idInKey + ":[] ??????????? Long ? String ?");
      }
    }
  }
}
