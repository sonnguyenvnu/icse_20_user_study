@Override public FileInfoEntity saveFile(InputStream fileStream,String fileName,String type,String creatorId) throws IOException {
  MessageDigest digest=DigestUtils.getMd5Digest();
  String suffix=fileName.contains(".") ? fileName.substring(fileName.lastIndexOf(".") + 1,fileName.length()) : "";
  StorePath path;
  int fileSize;
  try (InputStream tmp=new InputStream(){
    @Override public int read(    byte[] b,    int off,    int len) throws IOException {
      int r=super.read(b,off,len);
      digest.update(b,off,len);
      return r;
    }
    @Override public int read() throws IOException {
      return fileStream.read();
    }
    @Override public void close() throws IOException {
      fileStream.close();
      super.close();
    }
    @Override public int available() throws IOException {
      return fileStream.available();
    }
  }
){
    path=fastFileStorageClient.uploadFile(tmp,fileSize=tmp.available(),suffix,new HashSet<>());
  }
   String md5=Hex.encodeHexString(digest.digest());
  FileInfoEntity fileInfo=fileInfoService.createEntity();
  fileInfo.setLocation(path.getFullPath());
  fileInfo.setMd5(md5);
  fileInfo.setStatus(DataStatus.STATUS_ENABLED);
  fileInfo.setSize((long)fileSize);
  fileInfo.setName(fileName);
  fileInfo.setType(type);
  fileInfo.setCreatorId(creatorId);
  fileInfo.setCreateTimeNow();
  fileInfoService.insert(fileInfo);
  return fileInfo;
}
