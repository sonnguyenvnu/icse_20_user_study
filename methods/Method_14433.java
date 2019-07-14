protected void untar(File destDir,InputStream inputStream) throws IOException {
  TarInputStream tin=new TarInputStream(inputStream);
  TarEntry tarEntry=null;
  while ((tarEntry=tin.getNextEntry()) != null) {
    File destEntry=new File(destDir,tarEntry.getName());
    File parent=destEntry.getParentFile();
    if (!parent.exists()) {
      parent.mkdirs();
    }
    if (tarEntry.isDirectory()) {
      destEntry.mkdirs();
    }
 else {
      FileOutputStream fout=new FileOutputStream(destEntry);
      try {
        tin.copyEntryContents(fout);
      }
  finally {
        fout.close();
      }
    }
  }
  tin.close();
}
