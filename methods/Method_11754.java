private static void failNotNull(String message,Object actual){
  String formatted="";
  if (message != null) {
    formatted=message + " ";
  }
  fail(formatted + "expected null, but was:<" + actual + ">");
}
