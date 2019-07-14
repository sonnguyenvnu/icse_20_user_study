public void renderReports(final List<Renderer> renderers,final Report report){
  try (TimedOperation to=TimeTracker.startOperation(TimedOperationCategory.REPORTING)){
    for (    Renderer r : renderers) {
      r.renderFileReport(report);
    }
  }
 catch (  IOException ioe) {
    throw new RuntimeException(ioe);
  }
}
