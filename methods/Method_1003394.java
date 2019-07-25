private void process(String dir,String fileName) throws Exception {
  String inFile=inDir + "/" + dir + "/" + fileName;
  String outFile=outDir + "/" + dir + "/" + fileName;
  new File(outFile).getParentFile().mkdirs();
  FileOutputStream out=new FileOutputStream(outFile);
  FileInputStream in=new FileInputStream(inFile);
  byte[] bytes=IOUtils.readBytesAndClose(in,0);
  if (fileName.endsWith(".html")) {
    String page=new String(bytes);
    page=PageParser.parse(page,session);
    bytes=page.getBytes();
  }
  out.write(bytes);
  out.close();
}
