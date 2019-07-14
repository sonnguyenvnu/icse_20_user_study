/** 
 * Parses query from give query string. Values are optionally decoded.
 */
public static HttpMultiMap<String> parseQuery(final String query,final boolean decode){
  final HttpMultiMap<String> queryMap=HttpMultiMap.newCaseInsensitiveMap();
  if (StringUtil.isBlank(query)) {
    return queryMap;
  }
  int lastNdx=0;
  while (lastNdx < query.length()) {
    int ndx=query.indexOf('&',lastNdx);
    if (ndx == -1) {
      ndx=query.length();
    }
    final String paramAndValue=query.substring(lastNdx,ndx);
    ndx=paramAndValue.indexOf('=');
    if (ndx == -1) {
      queryMap.add(paramAndValue,null);
    }
 else {
      String name=paramAndValue.substring(0,ndx);
      if (decode) {
        name=URLDecoder.decodeQuery(name);
      }
      String value=paramAndValue.substring(ndx + 1);
      if (decode) {
        value=URLDecoder.decodeQuery(value);
      }
      queryMap.add(name,value);
    }
    lastNdx+=paramAndValue.length() + 1;
  }
  return queryMap;
}
