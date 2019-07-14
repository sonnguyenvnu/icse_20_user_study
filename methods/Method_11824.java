private static String stringValueOf(Object next){
  try {
    return String.valueOf(next);
  }
 catch (  Throwable e) {
    return "[toString failed]";
  }
}
