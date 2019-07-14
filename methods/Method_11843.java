@Override public String getMessage(){
  StringBuilder sb=new StringBuilder();
  if (fMessage != null) {
    sb.append(fMessage);
  }
  sb.append("arrays first differed at element ");
  for (  int each : fIndices) {
    sb.append("[");
    sb.append(each);
    sb.append("]");
  }
  sb.append("; ");
  sb.append(getCause().getMessage());
  return sb.toString();
}
