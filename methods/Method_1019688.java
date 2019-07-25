public void process(String reportId,String entryName,IEntryReaderProvider readerProvider,Writer writer,IContext context) throws XDocReportException, IOException {
  boolean useTemplateCache=isUseTemplateCache(reportId);
  long startTime=-1;
  if (LOGGER.isLoggable(Level.FINE)) {
    startTime=System.currentTimeMillis();
    LOGGER.fine(format("Start template engine id=%s for the entry=%s with template cache=%s",getId(),entryName,useTemplateCache));
  }
  Reader reader=null;
  try {
    writer=getWriter(writer);
    String templateName=getCachedTemplateName(reportId,entryName);
    if (useTemplateCache) {
      processWithCache(templateName,context,writer);
    }
 else {
      reader=readerProvider.getEntryReader(entryName);
      processNoCache(templateName,context,reader,writer);
    }
    if (LOGGER.isLoggable(Level.FINE)) {
      startTime=System.currentTimeMillis();
      LOGGER.fine(format("Result template engine id=" + getId() + "  for the entry=" + entryName + ": "));
      LOGGER.fine(prettyPrint(((MultiWriter)writer).getWriter(1).toString()));
      LOGGER.fine("End template engine id=" + getId() + " for the entry=" + entryName + " done with " + (System.currentTimeMillis() - startTime) + "(ms).");
    }
  }
 catch (  Throwable e) {
    if (LOGGER.isLoggable(Level.FINE)) {
      LOGGER.fine(("End template engine id=" + getId() + " for the entry=" + entryName + " done with " + (System.currentTimeMillis() - startTime) + "(ms)."));
    }
    if (e instanceof RuntimeException) {
      throw (RuntimeException)e;
    }
    if (e instanceof IOException) {
      throw (IOException)e;
    }
    if (e instanceof XDocReportException) {
      throw (XDocReportException)e;
    }
    throw new XDocReportException(e);
  }
 finally {
    if (reader != null) {
      IOUtils.closeQuietly(reader);
    }
    if (writer != null) {
      IOUtils.closeQuietly(writer);
    }
  }
}
