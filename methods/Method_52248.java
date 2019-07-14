private boolean isJUnitTest(ASTClassOrInterfaceType type){
  Class<?> clazz=type.getType();
  if (clazz == null) {
    if ("junit.framework.Test".equals(type.getImage())) {
      return true;
    }
  }
 else   if (isJUnitTest(clazz)) {
    return true;
  }
 else {
    while (clazz != null && !Object.class.equals(clazz)) {
      for (      Class<?> intf : clazz.getInterfaces()) {
        if (isJUnitTest(intf)) {
          return true;
        }
      }
      clazz=clazz.getSuperclass();
    }
  }
  return false;
}
