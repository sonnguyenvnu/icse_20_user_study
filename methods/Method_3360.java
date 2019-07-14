private static void processProperty(Object target,PropertyDescriptor property,List<String> arguments){
  Method writeMethod=property.getWriteMethod();
  if (writeMethod != null) {
    Argument argument=writeMethod.getAnnotation(Argument.class);
    if (argument != null) {
      boolean set=false;
      for (Iterator<String> i=arguments.iterator(); i.hasNext(); ) {
        String arg=i.next();
        String prefix=argument.prefix();
        String delimiter=argument.delimiter();
        if (arg.startsWith(prefix)) {
          Object value;
          String name=getName(argument,property);
          String alias=getAlias(argument);
          arg=arg.substring(prefix.length());
          Class<?> type=property.getPropertyType();
          if (arg.equals(name) || (alias != null && arg.equals(alias))) {
            i.remove();
            value=consumeArgumentValue(name,type,argument,i);
            if (!set) {
              setProperty(type,property,target,value,delimiter);
            }
 else {
              addPropertyArgument(type,property,target,value,delimiter);
            }
            set=true;
          }
          if (set && !type.isArray())           break;
        }
      }
      if (!set && argument.required()) {
        String name=getName(argument,property);
        throw new IllegalArgumentException("You must set argument " + name);
      }
    }
  }
}
