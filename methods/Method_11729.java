static public void failNotSame(String message,Object expected,Object actual){
  String formatted=(message != null) ? message + " " : "";
  fail(formatted + "expected same:<" + expected + "> was not:<" + actual + ">");
}
