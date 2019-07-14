public void checkAndMakeParentDirecotry(String fullName){
  int index=fullName.lastIndexOf(PATH_SEPERATOR);
  if (index > 0) {
    String path=fullName.substring(0,index);
    File file=new File(path);
    if (!file.exists()) {
      file.mkdirs();
    }
  }
}
