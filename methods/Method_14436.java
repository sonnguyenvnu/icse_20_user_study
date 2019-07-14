protected void copyFile(File file,OutputStream os) throws IOException {
  final int buffersize=4096;
  FileInputStream fis=new FileInputStream(file);
  try {
    byte[] buf=new byte[buffersize];
    int count;
    while ((count=fis.read(buf,0,buffersize)) != -1) {
      os.write(buf,0,count);
    }
  }
  finally {
    fis.close();
  }
}
