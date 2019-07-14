public int countFileRecursive(String fullname){
  File file_or_dir=new File(fullname);
  int sum=0;
  if (file_or_dir.isDirectory()) {
    for (    File file : file_or_dir.listFiles()) {
      sum+=countFileRecursive(file.getPath());
    }
  }
 else {
    if (file_or_dir.getPath().endsWith(suffix)) {
      sum+=1;
    }
  }
  return sum;
}
