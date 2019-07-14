@Override public void writeFile(String fileId,OutputStream out,long skip) throws IOException {
  try (InputStream inputStream=readFile(fileId)){
    if (skip > 0) {
      long len=inputStream.skip(skip);
      logger.info("skip write stream {},{}",skip,len);
    }
    StreamUtils.copy(inputStream,out);
  }
 }
