private File read(String name,InputStream is){
  final File sourceFile=new File(TMPDIR + File.separator + name);
  sourceFile.deleteOnExit();
  try {
    final FileOutputStream fos=new FileOutputStream(sourceFile);
    byte buf[]=new byte[1024];
    int len;
    while ((len=is.read(buf)) > 0) {
      fos.write(buf,0,len);
    }
    fos.close();
    is.close();
  }
 catch (  IOException e) {
    e.printStackTrace();
  }
  return sourceFile;
}
