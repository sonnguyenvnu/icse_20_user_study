HybridFileParcelable generateBaseFileFromParent(){
  ArrayList<HybridFileParcelable> arrayList=RootHelper.getFilesList(getFile().getParent(),true,true,null);
  for (  HybridFileParcelable baseFile : arrayList) {
    if (baseFile.getPath().equals(path))     return baseFile;
  }
  return null;
}
