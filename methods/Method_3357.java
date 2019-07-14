/** 
 * Parse a set of arguments and populate the target with the appropriate values.
 * @param target           Either an instance or a class
 * @param args             The arguments you want to parse and populate
 * @param failOnExtraFlags Throw an IllegalArgumentException if extra flags are present
 * @return The list of arguments that were not consumed
 */
public static List<String> parse(Object target,String[] args,boolean failOnExtraFlags){
  List<String> arguments=new ArrayList<String>();
  arguments.addAll(Arrays.asList(args));
  Class<?> clazz;
  if (target instanceof Class) {
    clazz=(Class)target;
  }
 else {
    clazz=target.getClass();
    try {
      BeanInfo info=Introspector.getBeanInfo(clazz);
      for (      PropertyDescriptor pd : info.getPropertyDescriptors()) {
        processProperty(target,pd,arguments);
      }
    }
 catch (    IntrospectionException e) {
    }
  }
  for (Class<?> currentClazz=clazz; currentClazz != null; currentClazz=currentClazz.getSuperclass()) {
    for (    Field field : currentClazz.getDeclaredFields()) {
      processField(target,field,arguments);
    }
  }
  if (failOnExtraFlags) {
    for (    String argument : arguments) {
      if (argument.startsWith("-")) {
        throw new IllegalArgumentException("????: " + argument);
      }
    }
  }
  return arguments;
}
