private void loadCustomDic(String customPath,boolean isCache){
  if (TextUtility.isBlank(customPath)) {
    return;
  }
  logger.info("?????????:" + customPath);
  DoubleArrayTrie<CoreDictionary.Attribute> dat=new DoubleArrayTrie<CoreDictionary.Attribute>();
  String path[]=customPath.split(";");
  String mainPath=path[0];
  StringBuilder combinePath=new StringBuilder();
  for (  String aPath : path) {
    combinePath.append(aPath.trim());
  }
  File file=new File(mainPath);
  mainPath=file.getParent() + "/" + Math.abs(combinePath.toString().hashCode());
  mainPath=mainPath.replace("\\","/");
  if (CustomDictionary.loadMainDictionary(mainPath,path,dat,isCache)) {
    this.setDat(dat);
  }
}
