public void loadFileRecursive(String fullname){
  int count=countFileRecursive(fullname);
  if (loadingProgress == null) {
    loadingProgress=new Progress(count,50);
  }
  File file_or_dir=new File(fullname);
  if (file_or_dir.isDirectory()) {
    for (    File file : file_or_dir.listFiles()) {
      loadFileRecursive(file.getPath());
    }
  }
 else {
    if (file_or_dir.getPath().endsWith(suffix)) {
      loadFile(file_or_dir.getPath());
    }
  }
}
