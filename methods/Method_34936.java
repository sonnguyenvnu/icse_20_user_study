/** 
 * Canonicalize the query string.
 * @param paramsMap Parameter map whose name-value pairs are in order of insertion.
 * @param charset Charset of html page
 * @return Canonical form of query string.
 * @throws UnsupportedEncodingException
 */
private static String canonicalize(Map<String,String> paramsMap,Charset charset) throws UnsupportedEncodingException {
  if ((paramsMap == null) || paramsMap.isEmpty()) {
    return "";
  }
  final StringBuilder sb=new StringBuilder(100);
  for (  Map.Entry<String,String> pair : paramsMap.entrySet()) {
    final String key=pair.getKey().toLowerCase();
    if ("jsessionid".equals(key) || "phpsessid".equals(key) || "aspsessionid".equals(key)) {
      continue;
    }
    if (sb.length() > 0) {
      sb.append('&');
    }
    sb.append(percentEncodeRfc3986(pair.getKey(),charset));
    if (!pair.getValue().isEmpty()) {
      sb.append('=');
      sb.append(percentEncodeRfc3986(pair.getValue(),charset));
    }
  }
  return sb.toString();
}
