public boolean apply(JSONSerializer serializer,Object source,String name){
  if (source == null) {
    return true;
  }
  if (clazz != null && !clazz.isInstance(source)) {
    return true;
  }
  if (this.excludes.contains(name)) {
    return false;
  }
  if (maxLevel > 0) {
    int level=0;
    SerialContext context=serializer.context;
    while (context != null) {
      level++;
      if (level > maxLevel) {
        return false;
      }
      context=context.parent;
    }
  }
  if (includes.size() == 0 || includes.contains(name)) {
    return true;
  }
  return false;
}
