private void addError(Report report,Exception e,String errorMessage){
  LOG.log(Level.FINE,errorMessage,e);
  report.addError(new Report.ProcessingError(e,fileName));
}
