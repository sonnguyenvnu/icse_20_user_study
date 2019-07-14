public static void state(boolean expression,String errorMessageFormat,Object... args){
  if (!expression)   throw new IllegalStateException(String.format(errorMessageFormat,args));
}
