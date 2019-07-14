protected String getCategoryStr(){
  StringBuilder sb=new StringBuilder();
  for (  String category : categories) {
    sb.append(category);
    sb.append(',');
  }
  sb.deleteCharAt(sb.length() - 1);
  return sb.toString();
}
