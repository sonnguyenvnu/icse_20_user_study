/** 
 * @deprecated prefer {@link #logFatalError(Log,Context)} 
 */
@Deprecated public void logFatalError(Log log){
  String version=ErrorProneVersion.loadVersionFromPom().or("unknown version");
  JavaFileObject prev=log.currentSourceFile();
  try {
    log.useSource(source);
    Method m=Log.class.getMethod("error",DiagnosticPosition.class,String.class,Object[].class);
    m.invoke(log,pos,"error.prone.crash",Throwables.getStackTraceAsString(cause),version,checkName);
  }
 catch (  ReflectiveOperationException e) {
    throw new LinkageError(e.getMessage(),e);
  }
 finally {
    log.useSource(prev);
  }
}
