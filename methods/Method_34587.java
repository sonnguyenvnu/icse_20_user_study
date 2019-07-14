static String getDefaultNameFromClass(Class<?> cls){
  String fromCache=defaultNameCache.get(cls);
  if (fromCache != null) {
    return fromCache;
  }
  String name=cls.getSimpleName();
  if (name.equals("")) {
    name=cls.getName();
    name=name.substring(name.lastIndexOf('.') + 1,name.length());
  }
  defaultNameCache.put(cls,name);
  return name;
}
