static public MultiFileReadingProgress createMultiFileReadingProgress(final ImportingJob job,List<ObjectNode> fileRecords){
  long totalSize=0;
  for (  ObjectNode fileRecord : fileRecords) {
    File file=ImportingUtilities.getFile(job,fileRecord);
    totalSize+=file.length();
  }
  final long totalSize2=totalSize;
  return new MultiFileReadingProgress(){
    void setProgress(    String fileSource,    long bytesRead){
      job.setProgress(totalSize2 == 0 ? -1 : (int)(100 * (totalBytesRead + bytesRead) / totalSize2),"Reading " + fileSource);
    }
    @Override public void startFile(    String fileSource){
      setProgress(fileSource,0);
    }
    @Override public void readingFile(    String fileSource,    long bytesRead){
      setProgress(fileSource,bytesRead);
    }
    @Override public void endFile(    String fileSource,    long bytesRead){
      totalBytesRead+=bytesRead;
    }
  }
;
}
