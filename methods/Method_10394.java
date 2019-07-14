protected static String throwableToString(Throwable t){
  if (t == null)   return null;
  StringWriter sw=new StringWriter();
  t.printStackTrace(new PrintWriter(sw));
  return sw.toString();
}
