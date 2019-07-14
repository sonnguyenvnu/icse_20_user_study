/** 
 * Takes a query string, separates the constituent name-value pairs, and stores them in a LinkedHashMap ordered by their original order.
 * @return Null if there is no query string.
 */
private static Map<String,String> createParameterMap(String queryString){
  if ((queryString == null) || queryString.isEmpty()) {
    return null;
  }
  final String[] pairs=queryString.split("&");
  final Map<String,String> params=new LinkedHashMap<>(pairs.length);
  for (  final String pair : pairs) {
    if (pair.isEmpty()) {
      continue;
    }
    String[] tokens=pair.split("=",2);
switch (tokens.length) {
case 1:
      if (pair.charAt(0) == '=') {
        params.put("",tokens[0]);
      }
 else {
        params.put(tokens[0],"");
      }
    break;
case 2:
  params.put(tokens[0],tokens[1]);
break;
}
}
return new LinkedHashMap<>(params);
}
