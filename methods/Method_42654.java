/** 
 * @param parameters
 * @param charSet
 * @return
 */
public static String mapToQueryString(Map parameters,String charSet){
  String queryString="";
  if (parameters != null && !parameters.isEmpty()) {
    Set<Entry> entrySet=parameters.entrySet();
    for (    Entry entry : entrySet) {
      try {
        String key=entry.getKey().toString();
        Object value=entry.getValue();
        List values=makeStringList(value);
        for (        Object v : values) {
          queryString+=key + "=" + URLEncoder.encode(v == null ? "" : v.toString(),charSet) + "&";
        }
      }
 catch (      UnsupportedEncodingException e) {
        throw new IllegalArgumentException("invalid charset : " + charSet);
      }
    }
    if (queryString.length() > 0) {
      queryString=queryString.substring(0,queryString.length() - 1);
    }
  }
  return queryString;
}
