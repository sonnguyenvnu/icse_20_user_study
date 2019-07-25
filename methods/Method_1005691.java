private static void check(boolean condition,String message){
  if (!condition) {
    throw new IllegalArgumentException(message);
  }
}
