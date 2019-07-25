private String concat(final List<String> strings){
  final StringBuilder sb=new StringBuilder();
  for (  String s : strings) {
    sb.append(StringUtils.trin(s));
  }
  return sb.toString();
}
