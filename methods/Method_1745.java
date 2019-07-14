private String getFilename(String resourceId){
  FileInfo fileInfo=new FileInfo(FileType.CONTENT,resourceId);
  String path=getSubdirectoryPath(fileInfo.resourceId);
  return fileInfo.toPath(path);
}
