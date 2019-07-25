public <T>Class<T> define(ClassDefiner cd,Class<T> klass){
  if (klass.getName().endsWith(CLASSNAME_SUFFIX))   return klass;
  String newName=klass.getName() + (id == null ? "" : "$" + id) + CLASSNAME_SUFFIX;
  return define(cd,klass,newName);
}
