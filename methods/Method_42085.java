@Override public boolean accept(File file){
  return file.isDirectory() && !file.isHidden() && !new File(file,".nomedia").exists();
}
