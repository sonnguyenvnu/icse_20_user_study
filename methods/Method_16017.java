public static Map<String,String> queryStringToMap(String queryString,String charset){
  try {
    Map<String,String> map=new HashMap<>();
    String[] decode=URLDecoder.decode(queryString,charset).split("&");
    for (    String keyValue : decode) {
      String[] kv=keyValue.split("[=]",2);
      map.put(kv[0],kv.length > 1 ? kv[1] : "");
    }
    return map;
  }
 catch (  UnsupportedEncodingException e) {
    throw new UnsupportedOperationException(e);
  }
}
