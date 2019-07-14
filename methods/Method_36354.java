private void listFiles(List<File> subFiles,File parent,String suffix){
  File[] files=parent.listFiles();
  if (files == null || files.length == 0) {
    return;
  }
  for (  File f : files) {
    if (f.isFile() && f.getName().endsWith(suffix)) {
      subFiles.add(f);
    }
 else     if (f.isDirectory()) {
      listFiles(subFiles,f,suffix);
    }
  }
}
