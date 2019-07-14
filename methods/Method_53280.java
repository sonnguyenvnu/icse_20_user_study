@Override public UploadedMedia uploadMediaChunked(String fileName,InputStream media) throws TwitterException {
  byte[] dataBytes=null;
  try {
    ByteArrayOutputStream baos=new ByteArrayOutputStream(256 * 1024);
    byte[] buffer=new byte[32768];
    int n;
    while ((n=media.read(buffer)) != -1) {
      baos.write(buffer,0,n);
    }
    dataBytes=baos.toByteArray();
    if (dataBytes.length > MAX_VIDEO_SIZE) {
      throw new TwitterException(String.format(Locale.US,"video file can't be longer than: %d MBytes",MAX_VIDEO_SIZE / MB));
    }
  }
 catch (  IOException ioe) {
    throw new TwitterException("Failed to download the file.",ioe);
  }
  try {
    UploadedMedia uploadedMedia=uploadMediaChunkedInit(dataBytes.length);
    ByteArrayInputStream dataInputStream=new ByteArrayInputStream(dataBytes);
    byte[] segmentData=new byte[CHUNK_SIZE];
    int segmentIndex=0;
    int totalRead=0;
    int bytesRead=0;
    while ((bytesRead=dataInputStream.read(segmentData)) > 0) {
      totalRead=totalRead + bytesRead;
      logger.debug("Chunked appened, segment index:" + segmentIndex + " bytes:" + totalRead + "/" + dataBytes.length);
      ByteArrayInputStream byteArrayInputStream=new ByteArrayInputStream(segmentData,0,bytesRead);
      uploadMediaChunkedAppend(fileName,byteArrayInputStream,segmentIndex,uploadedMedia.getMediaId());
      segmentData=new byte[CHUNK_SIZE];
      segmentIndex++;
    }
    return uploadMediaChunkedFinalize(uploadedMedia.getMediaId());
  }
 catch (  Exception e) {
    throw new TwitterException(e);
  }
}
