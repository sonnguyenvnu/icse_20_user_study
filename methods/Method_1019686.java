public byte[] download(String reportID,String processState) throws XDocReportException {
  XDocArchive archive=null;
  ByteArrayOutputStream out=new ByteArrayOutputStream();
  IXDocReport report=getXDocReportRegistry().getReport(reportID);
  if (report == null)   throw new XDocReportException("report not found " + reportID);
  if (ProcessState.ORIGINAL.name().equalsIgnoreCase(processState)) {
    archive=report.getOriginalDocumentArchive().createCopy();
  }
 else   if (ProcessState.PREPROCESSED.name().equalsIgnoreCase(processState)) {
    archive=report.getPreprocessedDocumentArchive().createCopy();
  }
 else {
    throw new XDocReportException("processState should be " + ProcessState.ORIGINAL + " or " + ProcessState.PREPROCESSED);
  }
  try {
    XDocArchive.writeZip(archive,out);
  }
 catch (  IOException e) {
    LOGGER.severe(e.getMessage());
    throw new XDocReportException(e.getMessage());
  }
  return out.toByteArray();
}
