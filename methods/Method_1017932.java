public static URI path(final URI uri,final String path){
  final String uriString=uri.toString();
  final StringBuilder sb=new StringBuilder(uriString);
  if (!uriString.endsWith("/")) {
    sb.append('/');
  }
  sb.append(path.startsWith("/") ? path.substring(1) : path);
  return URI.create(sb.toString());
}
