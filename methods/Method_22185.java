private static Object getProxyTargetObject(final Object proxy,final String proxyType){
  Field h;
  try {
    h=proxy.getClass().getSuperclass().getDeclaredField(proxyType);
  }
 catch (  final NoSuchFieldException ex) {
    return getProxyTargetObjectForCglibAndSpring4(proxy);
  }
  h.setAccessible(true);
  try {
    return getTargetObject(h.get(proxy));
  }
 catch (  final IllegalAccessException ex) {
    throw new JobSystemException(ex);
  }
}
