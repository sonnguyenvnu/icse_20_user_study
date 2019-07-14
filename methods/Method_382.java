public static List<Object> parseArray(String text,Type[] types){
  if (text == null) {
    return null;
  }
  List<Object> list;
  DefaultJSONParser parser=new DefaultJSONParser(text,ParserConfig.getGlobalInstance());
  Object[] objectArray=parser.parseArray(types);
  if (objectArray == null) {
    list=null;
  }
 else {
    list=Arrays.asList(objectArray);
  }
  parser.handleResovleTask(list);
  parser.close();
  return list;
}
