public static <T>T instantiate(String className,Object[] constructorArgs,Class[] classes){
  Preconditions.checkArgument(constructorArgs != null && classes != null);
  Preconditions.checkArgument(constructorArgs.length == classes.length);
  try {
    Class clazz=Class.forName(className);
    Constructor constructor=clazz.getConstructor(classes);
    return (T)constructor.newInstance(constructorArgs);
  }
 catch (  ClassNotFoundException e) {
    throw new IllegalArgumentException("Could not find implementation class: " + className,e);
  }
catch (  NoSuchMethodException e) {
    throw new IllegalArgumentException("Implementation class does not have required constructor: " + className,e);
  }
catch (  InstantiationException|IllegalAccessException|InvocationTargetException|ClassCastException e) {
    throw new IllegalArgumentException("Could not instantiate implementation: " + className,e);
  }
}
