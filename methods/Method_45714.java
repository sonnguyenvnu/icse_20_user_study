private static boolean isImplementOrSubclass(String interfaceName,Class<?> implementClass){
  Class<?>[] interfaces=implementClass.getInterfaces();
  if (interfaces.length > 0) {
    for (    Class<?> oneInterface : interfaces) {
      if (interfaceName.equals(oneInterface.getCanonicalName())) {
        return true;
      }
      if (isImplementOrSubclass(interfaceName,oneInterface)) {
        return true;
      }
    }
  }
  while (!Object.class.equals(implementClass)) {
    Class<?> superClass=implementClass.getSuperclass();
    if (superClass == null) {
      break;
    }
    implementClass=superClass;
    if (isImplementOrSubclass(interfaceName,implementClass)) {
      return true;
    }
  }
  return false;
}
