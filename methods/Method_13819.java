private String getExceptionString(List<Exception> exceptions){
  String ex="";
  for (  Exception e : exceptions) {
    ex=ex + e.getLocalizedMessage() + "\n";
  }
  return ex;
}
