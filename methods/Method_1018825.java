public static InjectResult eject(ClassLoader parentClassLoader,ClassLoader childClassLoader){
  boolean hasBaseDexClassLoader=true;
  try {
    Class.forName("dalvik.system.BaseDexClassLoader");
  }
 catch (  ClassNotFoundException e) {
    hasBaseDexClassLoader=false;
  }
  if (!hasBaseDexClassLoader) {
    return ejectBelowApiLevel14(parentClassLoader,childClassLoader);
  }
 else {
    return ejectAboveEqualApiLevel14(parentClassLoader,childClassLoader);
  }
}
