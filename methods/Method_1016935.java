public static java.lang.String[] process(java.lang.Class owner,java.lang.String[] args){
  CommandOption.List options=(CommandOption.List)class2options.get(owner);
  if (options == null)   throw new IllegalArgumentException("No CommandOptions registered for class " + owner);
  return options.process(args);
}
