public File[] _XXXXX_(){
  String[] rangeStoreDirNames=getRangeStoreDirNames();
  File[] rangeStoreDirs=new File[rangeStoreDirNames.length];
  for (int i=0; i < rangeStoreDirNames.length; i++) {
    rangeStoreDirs[i]=new File(rangeStoreDirNames[i]);
  }
  return rangeStoreDirs;
}