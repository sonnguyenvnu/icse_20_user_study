protected void tarDir(String relative,File dir,TarOutputStream tos) throws IOException {
  File[] files=dir.listFiles();
  for (  File file : files) {
    if (!file.isHidden()) {
      String path=relative + file.getName();
      if (file.isDirectory()) {
        tarDir(path + File.separator,file,tos);
      }
 else {
        TarEntry entry=new TarEntry(path);
        entry.setMode(TarEntry.DEFAULT_FILE_MODE);
        entry.setSize(file.length());
        entry.setModTime(file.lastModified());
        tos.putNextEntry(entry);
        copyFile(file,tos);
        tos.closeEntry();
      }
    }
  }
}
