private static String getFullStackTrace(Throwable exception){
  StringWriter stringWriter=new StringWriter();
  PrintWriter writer=new PrintWriter(stringWriter);
  exception.printStackTrace(writer);
  return stringWriter.toString();
}
