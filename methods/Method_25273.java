public void logFatalError(Log log,Context context){
  String version=ErrorProneVersion.loadVersionFromPom().or("unknown version");
  JavaFileObject originalSource=log.useSource(source);
  Factory factory=Factory.instance(context);
  try {
    log.report(factory.create(DiagnosticType.ERROR,log.currentSource(),pos,"error.prone.crash",Throwables.getStackTraceAsString(cause),version,checkName));
  }
  finally {
    log.useSource(originalSource);
  }
}
