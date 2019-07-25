@Override public File resolve(String name,boolean isModule){
  InputStream is=InJarFilenameToStream.class.getResourceAsStream(prefix + name);
  if (is != null) {
    try {
      File sourceFile=new File(TMPDIR + File.separator + name);
      sourceFile.deleteOnExit();
      FileOutputStream fos=new FileOutputStream(sourceFile);
      byte buf[]=new byte[1024];
      int len;
      while ((len=is.read(buf)) > 0) {
        fos.write(buf,0,len);
      }
      fos.close();
      is.close();
      return sourceFile;
    }
 catch (    IOException e) {
      e.printStackTrace();
    }
  }
  return super.resolve(name,isModule);
}
