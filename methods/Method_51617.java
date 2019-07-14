/** 
 * Return extracted parameters from dburi.
 * @param dburi
 * @return extracted parameters
 * @throws UnsupportedEncodingException
 */
private Map<String,String> getParameterMap(URI dburi) throws UnsupportedEncodingException {
  Map<String,String> map=new HashMap<>();
  String query=dburi.getRawQuery();
  LOGGER.log(Level.FINEST,"dburi,getQuery()={0}",query);
  if (null != query && !"".equals(query)) {
    String[] params=query.split("&");
    for (    String param : params) {
      String[] splits=param.split("=");
      String name=splits[0];
      String value=null;
      if (splits.length > 1) {
        value=splits[1];
      }
      map.put(name,(null == value) ? value : URLDecoder.decode(value,"UTF-8"));
    }
  }
  return map;
}
