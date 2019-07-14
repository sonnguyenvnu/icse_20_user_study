private void listCloud(String path,CloudStorage cloudStorage,OpenMode openMode,OnFileFound fileFoundCallback) throws CloudPluginException {
  if (!CloudSheetFragment.isCloudProviderAvailable(c)) {
    throw new CloudPluginException();
  }
  CloudUtil.getCloudFiles(path,cloudStorage,openMode,fileFoundCallback);
}
