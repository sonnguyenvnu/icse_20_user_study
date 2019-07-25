public void load(InputStream sourceStream) throws IOException, XDocReportException {
  if (preprocessed) {
    preprocessed=false;
  }
  setDocumentArchive(XDocArchive.readZip(sourceStream));
}
