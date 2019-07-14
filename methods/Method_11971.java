private Request errorReport(Throwable cause){
  return Request.errorReport(JUnitCommandLineParseResult.class,cause);
}
