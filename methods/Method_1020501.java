public void fatal(FatalError fatalError,Object... args){
  checkArgument(fatalError.getNumberOfArguments() == args.length);
  problemsBySeverity.put(Severity.ERROR,"Error: " + String.format(fatalError.getMessage(),args));
  abort();
}
