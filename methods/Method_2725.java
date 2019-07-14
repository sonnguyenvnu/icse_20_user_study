public static SimpleItem create(String param){
  if (param == null)   return null;
  String[] array=param.split(" ");
  return create(array);
}
