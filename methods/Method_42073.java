public boolean isHidden(){
  return new File(path,".nomedia").exists();
}
