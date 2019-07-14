private static void processField(Object target,Field field,List<String> arguments){
  Argument argument=field.getAnnotation(Argument.class);
  if (argument != null) {
    boolean set=false;
    for (Iterator<String> i=arguments.iterator(); i.hasNext(); ) {
      String arg=i.next();
      String prefix=argument.prefix();
      String delimiter=argument.delimiter();
      if (arg.startsWith(prefix)) {
        Object value;
        String name=getName(argument,field);
        String alias=getAlias(argument);
        arg=arg.substring(prefix.length());
        Class<?> type=field.getType();
        if (arg.equals(name) || (alias != null && arg.equals(alias))) {
          i.remove();
          value=consumeArgumentValue(name,type,argument,i);
          if (!set) {
            setField(type,field,target,value,delimiter);
          }
 else {
            addArgument(type,field,target,value,delimiter);
          }
          set=true;
        }
        if (set && !type.isArray())         break;
      }
    }
    if (!set && argument.required()) {
      String name=getName(argument,field);
      throw new IllegalArgumentException("??????: " + argument.prefix() + name);
    }
  }
}
