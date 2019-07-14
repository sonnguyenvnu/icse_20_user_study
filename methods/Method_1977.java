private static void appendSize(StringBuilder sb,long bytes){
  String value=String.format(Locale.getDefault(),"%.1f MB",(float)bytes / BYTES_IN_MEGABYTE);
  sb.append(value);
}
