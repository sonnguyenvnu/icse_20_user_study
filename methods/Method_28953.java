public static String toURL(String protocol,String host,int port,String path){
  StringBuilder sb=new StringBuilder();
  sb.append(protocol).append("://");
  sb.append(host).append(':').append(port);
  if (path.charAt(0) != '/')   sb.append('/');
  sb.append(path);
  return sb.toString();
}
