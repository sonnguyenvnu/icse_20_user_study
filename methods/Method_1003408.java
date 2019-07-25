private static void add(Properties prop,String document,StringBuilder text){
  String s=clean(text.toString());
  text.setLength(0);
  prop.setProperty(document,s);
}
