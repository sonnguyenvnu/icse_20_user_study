private static void processField(Object target,Field field,Properties arguments){
  Argument argument=field.getAnnotation(Argument.class);
  if (argument != null) {
    String name=Args.getName(argument,field);
    String alias=Args.getAlias(argument);
    Class type=field.getType();
    Object value=arguments.get(name);
    if (value == null && alias != null) {
      value=arguments.get(alias);
    }
    if (value != null) {
      if (type == Boolean.TYPE || type == Boolean.class) {
        value=true;
      }
      Args.setField(type,field,target,value,argument.delimiter());
    }
 else {
      if (argument.required()) {
        throw new IllegalArgumentException("You must set argument " + name);
      }
    }
  }
}
