private FileFilter filesOnly(){
  return new FileFilter(){
    public boolean accept(    File file){
      return file.isFile();
    }
  }
;
}
