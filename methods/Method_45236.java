private String stackTraceToString(final Throwable e){
  StringWriter writer=new StringWriter();
  e.printStackTrace(new PrintWriter(writer));
  return writer.toString();
}
