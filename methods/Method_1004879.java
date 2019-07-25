public String serialise(){
  StringBuilder sb=new StringBuilder(HEADER);
  String sep="";
  for (  final byte[] auth : authsList) {
    sb.append(sep);
    sep=",";
    sb.append(Base64.getEncoder().encodeToString(auth));
  }
  return sb.toString();
}
