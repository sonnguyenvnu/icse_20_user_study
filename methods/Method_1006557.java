static String stacktrace(Exception e){
  StringWriter writer=new StringWriter();
  e.printStackTrace(new PrintWriter(writer));
  return writer.toString();
}
