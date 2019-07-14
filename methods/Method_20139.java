@Nullable private static Constructor<?> findConstructorForClass(Class<?> controllerClass){
  Constructor<?> helperCtor=BINDINGS.get(controllerClass);
  if (helperCtor != null || BINDINGS.containsKey(controllerClass)) {
    return helperCtor;
  }
  String clsName=controllerClass.getName();
  if (clsName.startsWith("android.") || clsName.startsWith("java.")) {
    return null;
  }
  try {
    Class<?> bindingClass=Class.forName(clsName + GENERATED_HELPER_CLASS_SUFFIX);
    helperCtor=bindingClass.getConstructor(controllerClass);
  }
 catch (  ClassNotFoundException e) {
    helperCtor=findConstructorForClass(controllerClass.getSuperclass());
  }
catch (  NoSuchMethodException e) {
    throw new RuntimeException("Unable to find Epoxy Helper constructor for " + clsName,e);
  }
  BINDINGS.put(controllerClass,helperCtor);
  return helperCtor;
}
