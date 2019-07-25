public static void state(boolean expression,String errorMessage,Object... args){
  if (!expression)   throw new IllegalStateException(String.format(errorMessage,args));
}
