/** 
 * Builds a query string from given query map.
 */
public static String buildQuery(final HttpMultiMap<?> queryMap,final String encoding){
  if (queryMap.isEmpty()) {
    return StringPool.EMPTY;
  }
  int queryMapSize=queryMap.size();
  StringBand query=new StringBand(queryMapSize * 4);
  int count=0;
  for (  Map.Entry<String,?> entry : queryMap) {
    String key=entry.getKey();
    key=URLCoder.encodeQueryParam(key,encoding);
    Object value=entry.getValue();
    if (value == null) {
      if (count != 0) {
        query.append('&');
      }
      query.append(key);
      count++;
    }
 else {
      if (count != 0) {
        query.append('&');
      }
      query.append(key);
      count++;
      query.append('=');
      String valueString=URLCoder.encodeQueryParam(value.toString(),encoding);
      query.append(valueString);
    }
  }
  return query.toString();
}
