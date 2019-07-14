static protected void unzipEntry(ZipInputStream zin,File f) throws IOException {
  FileOutputStream out=new FileOutputStream(f);
  byte[] b=new byte[512];
  int len=0;
  while ((len=zin.read(b)) != -1) {
    out.write(b,0,len);
  }
  out.flush();
  out.close();
}
