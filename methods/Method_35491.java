/** 
 * Populates the internal map of the JSONObject with the bean properties. The bean can not be recursive.
 * @see JSONObject#JSONObject(Object)
 * @param bean the bean
 */
private void populateMap(Object bean){
  Class<?> klass=bean.getClass();
  boolean includeSuperClass=klass.getClassLoader() != null;
  Method[] methods=includeSuperClass ? klass.getMethods() : klass.getDeclaredMethods();
  for (  final Method method : methods) {
    final int modifiers=method.getModifiers();
    if (Modifier.isPublic(modifiers) && !Modifier.isStatic(modifiers) && method.getParameterTypes().length == 0 && !method.isBridge() && method.getReturnType() != Void.TYPE && isValidMethodName(method.getName())) {
      final String key=getKeyNameFromMethod(method);
      if (key != null && !key.isEmpty()) {
        try {
          final Object result=method.invoke(bean);
          if (result != null) {
            this.map.put(key,wrap(result));
            if (result instanceof Closeable) {
              try {
                ((Closeable)result).close();
              }
 catch (              IOException ignore) {
              }
            }
          }
        }
 catch (        IllegalAccessException ignore) {
        }
catch (        IllegalArgumentException ignore) {
        }
catch (        InvocationTargetException ignore) {
        }
      }
    }
  }
}
