private static Object parse(Type type,Reader reader){
  Object obj=fromJson(reader);
  if (type != null)   return Mapl.maplistToObj(obj,type);
  return obj;
}
