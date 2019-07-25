public static boolean regex(String regex,String str){
  Pattern p=Pattern.compile(regex,Pattern.MULTILINE);
  Matcher m=p.matcher(str);
  return m.find();
}
