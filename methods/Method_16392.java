@Override @SuppressWarnings("all") public FileInfoEntity saveFile(InputStream fileStream,String fileName,String type,String creatorId) throws IOException {
  String fileBasePath=getFilePath();
  String filePath=DateFormatter.toString(new Date(),"yyyyMMdd");
  String absPath=fileBasePath.concat("/").concat(filePath);
  File path=new File(absPath);
  if (!path.exists()) {
    path.mkdirs();
  }
  String newName=String.valueOf(System.nanoTime());
  String fileAbsName=absPath.concat("/").concat(newName);
  int fileSize;
  MessageDigest digest=DigestUtils.getMd5Digest();
  try (InputStream proxyStream=new InputStream(){
    @Override public int read(    byte[] b,    int off,    int len) throws IOException {
      int l=fileStream.read(b,off,len);
      digest.update(b,off,len);
      return l;
    }
    @Override public void close() throws IOException {
      fileStream.close();
      super.close();
    }
    @Override public int available() throws IOException {
      return fileStream.available();
    }
    @Override public int read() throws IOException {
      return fileStream.read();
    }
  }
;FileOutputStream os=new FileOutputStream(fileAbsName)){
    int remainBytes=fileSize=proxyStream.available();
    byte[] buff=new byte[remainBytes > 1024 * 10 ? 1024 * 10 : remainBytes];
    int bytes;
    logger.info("??????:{}?:{}, size: {} bytes",fileName,fileAbsName,fileSize);
    while (remainBytes > 0) {
      bytes=proxyStream.read(buff,0,remainBytes > buff.length ? buff.length : remainBytes);
      os.write(buff,0,bytes);
      remainBytes-=bytes;
      logger.info("????:{}:{},?????: {} bytes",fileName,fileAbsName,remainBytes);
    }
  }
   String md5=Hex.encodeHexString(digest.digest());
  File newFile=new File(fileAbsName);
  FileInfoEntity fileInfo=fileInfoService.selectByMd5(md5);
  if (fileInfo != null) {
    logger.info("??:{}????",fileAbsName);
    if (new File(getFilePath() + "/" + fileInfo.getLocation()).exists()) {
      newFile.delete();
    }
 else {
      newFile.renameTo(new File(absPath.concat("/").concat(md5)));
    }
    return fileInfo;
  }
 else {
    logger.info("????{}??:{}->{}",fileName,fileAbsName,absPath.concat("/").concat(md5));
    newFile.renameTo(new File(absPath.concat("/").concat(md5)));
  }
  FileInfoEntity infoEntity=fileInfoService.createEntity();
  infoEntity.setCreateTimeNow();
  infoEntity.setCreatorId(creatorId);
  infoEntity.setLocation(filePath.concat("/").concat(md5));
  infoEntity.setName(fileName);
  infoEntity.setType(type);
  infoEntity.setSize((long)fileSize);
  infoEntity.setMd5(md5);
  infoEntity.setStatus(DataStatus.STATUS_ENABLED);
  fileInfoService.insert(infoEntity);
  return infoEntity;
}
