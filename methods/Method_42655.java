/** 
 * @param queryString
 * @param charSet
 * @return
 */
public static Map queryStringToMap(String queryString,String charSet){
  if (queryString == null) {
    throw new IllegalArgumentException("queryString must be specified");
  }
  int index=queryString.indexOf("?");
  if (index > 0) {
    queryString=queryString.substring(index + 1);
  }
  String[] keyValuePairs=queryString.split("&");
  Map<String,String> map=new HashMap<String,String>();
  for (  String keyValue : keyValuePairs) {
    if (keyValue.indexOf("=") == -1) {
      continue;
    }
    String[] args=keyValue.split("=");
    if (args.length == 2) {
      try {
        map.put(args[0],URLDecoder.decode(args[1],charSet));
      }
 catch (      UnsupportedEncodingException e) {
        throw new IllegalArgumentException("invalid charset : " + charSet);
      }
    }
    if (args.length == 1) {
      map.put(args[0],"");
    }
  }
  return map;
}
