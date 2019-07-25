public static String unlines(List<String> list){
  StringBuilder sb=new StringBuilder();
  list.intersperse(lineSeparator).foreachDoEffect(sb::append);
  return sb.toString();
}
