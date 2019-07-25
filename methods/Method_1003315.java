private void process(Connection conn,String fileName,boolean continueOnError,Charset charset) throws SQLException, IOException {
  InputStream in=FileUtils.newInputStream(fileName);
  String path=FileUtils.getParent(fileName);
  try {
    in=new BufferedInputStream(in,Constants.IO_BUFFER_SIZE);
    Reader reader=new InputStreamReader(in,charset);
    process(conn,continueOnError,path,reader,charset);
  }
  finally {
    IOUtils.closeSilently(in);
  }
}
