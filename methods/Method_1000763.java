public String get(String key,NutBean context){
  Object obj=super.get(key);
  if (null == obj)   return key;
  return Tmpl.exec(obj.toString(),context);
}
