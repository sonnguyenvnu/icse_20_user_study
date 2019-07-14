@Nullable @CheckResult @UiThread private static Constructor<? extends Unbinder> findBindingConstructorForClass(Class<?> cls){
  Constructor<? extends Unbinder> bindingCtor=BINDINGS.get(cls);
  if (bindingCtor != null || BINDINGS.containsKey(cls)) {
    if (debug)     Log.d(TAG,"HIT: Cached in binding map.");
    return bindingCtor;
  }
  String clsName=cls.getName();
  if (clsName.startsWith("android.") || clsName.startsWith("java.") || clsName.startsWith("androidx.")) {
    if (debug)     Log.d(TAG,"MISS: Reached framework class. Abandoning search.");
    return null;
  }
  try {
    Class<?> bindingClass=cls.getClassLoader().loadClass(clsName + "_ViewBinding");
    bindingCtor=(Constructor<? extends Unbinder>)bindingClass.getConstructor(cls,View.class);
    if (debug)     Log.d(TAG,"HIT: Loaded binding class and constructor.");
  }
 catch (  ClassNotFoundException e) {
    if (debug)     Log.d(TAG,"Not found. Trying superclass " + cls.getSuperclass().getName());
    bindingCtor=findBindingConstructorForClass(cls.getSuperclass());
  }
catch (  NoSuchMethodException e) {
    throw new RuntimeException("Unable to find binding constructor for " + clsName,e);
  }
  BINDINGS.put(cls,bindingCtor);
  return bindingCtor;
}
