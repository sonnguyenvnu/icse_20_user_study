public static String join(String[] stringArray){
  StringBuilder sb=new StringBuilder();
  for (  String s : stringArray) {
    sb.append(s);
  }
  return sb.toString();
}
