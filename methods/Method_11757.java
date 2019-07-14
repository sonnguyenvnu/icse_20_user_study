private static void failNotSame(String message,Object expected,Object actual){
  String formatted="";
  if (message != null) {
    formatted=message + " ";
  }
  fail(formatted + "expected same:<" + expected + "> was not:<" + actual + ">");
}
