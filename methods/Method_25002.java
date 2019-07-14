/** 
 * Decode parameters from a URL, handing the case where a single parameter name might have been supplied several times, by return lists of values. In general these lists will contain a single element.
 * @param queryString a query string pulled from the URL.
 * @return a map of <code>String</code> (parameter name) to<code>List&lt;String&gt;</code> (a list of the values supplied).
 */
protected static Map<String,List<String>> decodeParameters(String queryString){
  Map<String,List<String>> parms=new HashMap<String,List<String>>();
  if (queryString != null) {
    StringTokenizer st=new StringTokenizer(queryString,"&");
    while (st.hasMoreTokens()) {
      String e=st.nextToken();
      int sep=e.indexOf('=');
      String propertyName=sep >= 0 ? decodePercent(e.substring(0,sep)).trim() : decodePercent(e).trim();
      if (!parms.containsKey(propertyName)) {
        parms.put(propertyName,new ArrayList<String>());
      }
      String propertyValue=sep >= 0 ? decodePercent(e.substring(sep + 1)) : null;
      if (propertyValue != null) {
        parms.get(propertyName).add(propertyValue);
      }
    }
  }
  return parms;
}
