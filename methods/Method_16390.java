@Override public InputStream readFile(String fileIdOrMd5){
  FileInfoEntity fileInfo=fileInfoService.selectByIdOrMd5(fileIdOrMd5);
  if (fileInfo == null || !DataStatus.STATUS_ENABLED.equals(fileInfo.getStatus())) {
    throw new NotFoundException("file not found or disabled");
  }
  String filePath=getFilePath() + "/" + fileInfo.getLocation();
  File file=new File(filePath);
  if (!file.exists()) {
    throw new NotFoundException("file not found");
  }
  try {
    return new FileInputStream(file);
  }
 catch (  FileNotFoundException ignore) {
    throw new NotFoundException("file not found");
  }
}
