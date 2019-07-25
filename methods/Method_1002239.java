/** 
 * Applies the query string to the given URI, using the given separator between parameters. <p> A copy of the given URI is taken and its existing query string, if there is one, is replaced. The query string parameters are separated using the given <code>Separator</code>.
 * @param uri URI to copy and update
 * @param separator separator to use between parameters
 * @return a copy of the given URI, with an updated query string
 */
public URI apply(URI uri,Separator separator){
  StringBuilder builder=new StringBuilder();
  if (uri.getScheme() != null) {
    builder.append(uri.getScheme());
    builder.append(':');
  }
  if (uri.getHost() != null) {
    builder.append("//");
    if (uri.getUserInfo() != null) {
      builder.append(uri.getUserInfo());
      builder.append('@');
    }
    builder.append(uri.getHost());
    if (uri.getPort() != -1) {
      builder.append(':');
      builder.append(uri.getPort());
    }
  }
 else   if (uri.getAuthority() != null) {
    builder.append("//");
    builder.append(uri.getAuthority());
  }
  if (uri.getPath() != null) {
    builder.append(uri.getPath());
  }
  String query=toString(separator);
  if (query.length() != 0) {
    builder.append('?');
    builder.append(query);
  }
  if (uri.getFragment() != null) {
    builder.append('#');
    builder.append(uri.getFragment());
  }
  try {
    return new URI(builder.toString());
  }
 catch (  URISyntaxException e) {
    throw new RuntimeException(e);
  }
}
