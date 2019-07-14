private static Method findDeclaredMethod(final Class c,final String methodName,final boolean publicOnly){
  if ((methodName == null) || (c == null)) {
    return null;
  }
  Method[] ms=publicOnly ? c.getMethods() : c.getDeclaredMethods();
  for (  Method m : ms) {
    if (m.getName().equals(methodName)) {
      return m;
    }
  }
  return null;
}
