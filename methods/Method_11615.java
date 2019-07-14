public File getFile(String fullName){
  checkAndMakeParentDirecotry(fullName);
  return new File(fullName);
}
