private static void fieldUsage(PrintStream errStream,Object target,Field field){
  Argument argument=field.getAnnotation(Argument.class);
  if (argument != null) {
    String name=getName(argument,field);
    String alias=getAlias(argument);
    String prefix=argument.prefix();
    String delimiter=argument.delimiter();
    String description=argument.description();
    makeAccessible(field);
    try {
      Object defaultValue=field.get(target);
      Class<?> type=field.getType();
      propertyUsage(errStream,prefix,name,alias,type,delimiter,description,defaultValue);
    }
 catch (    IllegalAccessException e) {
      throw new IllegalArgumentException("Could not use thie field " + field + " as an argument field",e);
    }
  }
}
