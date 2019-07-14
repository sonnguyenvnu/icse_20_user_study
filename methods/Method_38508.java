/** 
 * Returns full URL path. Simply concatenates  {@link #protocol(String) protocol},  {@link #host(String) host}, {@link #port(int) port},  {@link #path(String) path} and {@link #queryString(String) query string}.
 */
public String url(){
  StringBuilder url=new StringBuilder();
  url.append(hostUrl());
  if (path != null) {
    url.append(path);
  }
  String queryString=queryString();
  if (StringUtil.isNotBlank(queryString)) {
    url.append('?');
    url.append(queryString);
  }
  return url.toString();
}
