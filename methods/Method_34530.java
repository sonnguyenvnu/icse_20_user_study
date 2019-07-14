private static int position(Type type,List<Type> types){
  if (type == null)   return -1;
  if (types == null || types.isEmpty())   return -1;
  return types.indexOf(type);
}
