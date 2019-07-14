@Override public String saveStaticFile(InputStream fileStream,String fileName) throws IOException {
  String suffix=fileName.contains(".") ? fileName.substring(fileName.lastIndexOf(".") + 1,fileName.length()) : "";
  StorePath path=fastFileStorageClient.uploadFile(fileStream,fileStream.available(),suffix,new HashSet<>());
  return staticLocation.concat(path.getFullPath());
}
