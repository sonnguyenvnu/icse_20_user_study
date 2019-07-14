public static Map.Entry<String,Map.Entry<String,Integer>[]> create(String param){
  if (param == null)   return null;
  String[] array=param.split(" ");
  return create(array);
}
