/** 
 * Generate report by merging Java model frm the context with XML Document (odt, docx...) preprocessed and store the result into output stream.
 */
public void process(IContext context,String entryName,OutputStream out) throws XDocReportException, IOException {
  long startTime=-1;
  if (LOGGER.isLoggable(Level.FINE)) {
    startTime=System.currentTimeMillis();
    LOGGER.fine("Start process report ");
  }
  XDocArchive outputArchive=null;
  try {
    internalGetTemplateEngine();
    doPreprocessorIfNeeded();
    outputArchive=internalGetDocumentArchive().createCopy();
    processTemplateEngine(context,outputArchive);
    doPostprocessIfNeeded(outputArchive);
    if (StringUtils.isNotEmpty(entryName)) {
      if (!outputArchive.hasEntry(entryName)) {
        throw new XDocReportException("No entry for the entry name=" + entryName);
      }
      XDocArchive.writeEntry(outputArchive,entryName,out);
    }
 else {
      XDocArchive.writeZip(outputArchive,out);
    }
    if (LOGGER.isLoggable(Level.FINE)) {
      LOGGER.fine("End process report done with " + (System.currentTimeMillis() - startTime) + "(ms).");
    }
  }
 catch (  Throwable e) {
    if (LOGGER.isLoggable(Level.FINE)) {
      LOGGER.fine("End process report with error done with " + (System.currentTimeMillis() - startTime) + "(ms).");
      LOGGER.throwing(getClass().getName(),"process",e);
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
    if (outputArchive != null) {
      outputArchive.dispose();
    }
    outputArchive=null;
  }
}
