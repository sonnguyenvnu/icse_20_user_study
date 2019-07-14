private <T extends Number>T tradesArgument(Object[] args,int index,Function<String,T> converter){
  if (index >= args.length) {
    return null;
  }
  Object arg=args[index];
  if (arg == null) {
    return null;
  }
  String argStr=arg.toString();
  try {
    return converter.apply(argStr);
  }
 catch (  NumberFormatException e) {
    throw new IllegalArgumentException("Argument on index " + index + " is not a number: " + argStr,e);
  }
}
