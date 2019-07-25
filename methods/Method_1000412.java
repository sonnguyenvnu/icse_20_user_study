public <T>T insert(T obj,String actived){
  Object first=Lang.first(obj);
  if (null == first)   return null;
  if (Strings.isBlank(actived))   return insert(obj);
  return insert(obj,FieldFilter.create(first.getClass(),actived));
}
