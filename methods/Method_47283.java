public static void getCloudFiles(String path,CloudStorage cloudStorage,OpenMode openMode,OnFileFound fileFoundCallback) throws CloudPluginException {
  String strippedPath=stripPath(openMode,path);
  try {
    for (    CloudMetaData cloudMetaData : cloudStorage.getChildren(strippedPath)) {
      HybridFileParcelable baseFile=new HybridFileParcelable(path + "/" + cloudMetaData.getName(),"",(cloudMetaData.getModifiedAt() == null) ? 0l : cloudMetaData.getModifiedAt(),cloudMetaData.getSize(),cloudMetaData.getFolder());
      baseFile.setName(cloudMetaData.getName());
      baseFile.setMode(openMode);
      fileFoundCallback.onFileFound(baseFile);
    }
  }
 catch (  Exception e) {
    e.printStackTrace();
    throw new CloudPluginException();
  }
}
