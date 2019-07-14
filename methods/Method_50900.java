public void report(String content) throws ReportException {
  try (Writer writer=new BufferedWriter(new OutputStreamWriter(getOutputStream(),encoding))){
    writer.write(content);
  }
 catch (  IOException ioe) {
    throw new ReportException(ioe);
  }
}
