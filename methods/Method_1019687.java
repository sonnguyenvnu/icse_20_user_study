public void process(String reportId,String entryName,IEntryReaderProvider readerProvider,IEntryWriterProvider writerProvider,IContext context) throws XDocReportException, IOException {
  Writer writer=writerProvider.getEntryWriter(entryName);
  process(reportId,entryName,readerProvider,writer,context);
}
