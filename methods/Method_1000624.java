protected static FastMethod make(Constructor<?> constructor){
  Class<?> klass=constructor.getDeclaringClass();
  String descriptor=Type.getConstructorDescriptor(constructor) + constructor.getDeclaringClass().getClassLoader();
  ;
  String key=Lang.md5(descriptor);
  String className=klass.getName() + "$FC$" + key;
  if (klass.getName().startsWith("java"))   className=FastMethod.class.getPackage().getName() + ".fast." + className;
  FastMethod fm=(FastMethod)cache.get(className);
  if (fm != null)   return fm;
  try {
    fm=(FastMethod)klass.getClassLoader().loadClass(className).newInstance();
    cache.put(key,fm);
    return fm;
  }
 catch (  Throwable e) {
  }
  try {
    byte[] buf=_make(klass,constructor.getModifiers(),constructor.getParameterTypes(),_Method(constructor),null,className,descriptor);
    Class<?> t=DefaultClassDefiner.defaultOne().define(className,buf,klass.getClassLoader());
    fm=(FastMethod)t.newInstance();
  }
 catch (  Throwable e) {
    if (log.isTraceEnabled())     log.trace("Fail to create FastMethod for " + constructor,e);
    fm=new FallbackFastMethod(constructor);
  }
  cache.put(className,fm);
  return fm;
}
