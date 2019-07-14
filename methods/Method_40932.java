public static void isTrue(boolean expression,String errorMessageFormat,Object... args){
  if (!expression)   throw new IllegalArgumentException(String.format(errorMessageFormat,args));
}
