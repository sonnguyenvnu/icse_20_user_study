@Override public InputStream readFile(String fileIdOrMd5){
  FileInfoEntity entity=fileInfoService.selectByIdOrMd5(fileIdOrMd5);
  StorePath path=StorePath.praseFromUrl(entity.getLocation());
  return fastFileStorageClient.downloadFile(path.getGroup(),path.getPath(),ins -> ins);
}
