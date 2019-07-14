public static long folderSizeCloud(OpenMode openMode,CloudMetaData sourceFileMeta){
  DataUtils dataUtils=DataUtils.getInstance();
  long length=0;
  CloudStorage cloudStorage=dataUtils.getAccount(openMode);
  for (  CloudMetaData metaData : cloudStorage.getChildren(CloudUtil.stripPath(openMode,sourceFileMeta.getPath()))) {
    if (metaData.getFolder()) {
      length+=folderSizeCloud(openMode,metaData);
    }
 else {
      length+=metaData.getSize();
    }
  }
  return length;
}
