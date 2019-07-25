protected static FastMethod make(final Method method){
  Class<?> klass=method.getDeclaringClass();
  String descriptor=Type.getMethodDescriptor(method) + method.getDeclaringClass().getClassLoader();
  String key="$FM$" + method.getName() + "$" + Lang.md5(descriptor);
  String className=klass.getName() + key;
  if (klass.getName().startsWith("java"))   className=FastMethod.class.getPackage().getName() + ".fast." + className;
  FastMethod fm=cache.get(className);
  if (fm != null)   return fm;
  if (!Modifier.isPublic(klass.getModifiers())) {
    fm=new FallbackFastMethod(method);
    cache.put(className,fm);
    return fm;
  }
  try {
    fm=(FastMethod)klass.getClassLoader().loadClass(className).newInstance();
    cache.put(className,fm);
    return fm;
  }
 catch (  Throwable e) {
  }
  try {
    byte[] buf=_make(klass,method.getModifiers(),method.getParameterTypes(),_Method(method),method.getReturnType(),className,descriptor);
    Class<?> t=DefaultClassDefiner.defaultOne().define(className,buf,klass.getClassLoader());
    fm=(FastMethod)t.newInstance();
  }
 catch (  Throwable e) {
    if (log.isTraceEnabled())     log.trace("Fail to create FastMethod for " + method,e);
    fm=new FallbackFastMethod(method);
  }
  cache.put(className,fm);
  return fm;
}
