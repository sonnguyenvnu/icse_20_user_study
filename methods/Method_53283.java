private UploadedMedia uploadMediaChunkedFinalize(long mediaId) throws TwitterException {
  int tries=0;
  int maxTries=20;
  int lastProgressPercent=0;
  int currentProgressPercent=0;
  UploadedMedia uploadedMedia=uploadMediaChunkedFinalize0(mediaId);
  while (tries < maxTries) {
    if (lastProgressPercent == currentProgressPercent) {
      tries++;
    }
    lastProgressPercent=currentProgressPercent;
    String state=uploadedMedia.getProcessingState();
    if (state.equals("failed")) {
      throw new TwitterException("Failed to finalize the chuncked upload.");
    }
    if (state.equals("pending") || state.equals("in_progress")) {
      currentProgressPercent=uploadedMedia.getProgressPercent();
      int waitSec=Math.max(uploadedMedia.getProcessingCheckAfterSecs(),1);
      logger.debug("Chunked finalize, wait for:" + waitSec + " sec");
      try {
        Thread.sleep(waitSec * 1000);
      }
 catch (      InterruptedException e) {
        throw new TwitterException("Failed to finalize the chuncked upload.",e);
      }
    }
    if (state.equals("succeeded")) {
      return uploadedMedia;
    }
    uploadedMedia=uploadMediaChunkedStatus(mediaId);
  }
  throw new TwitterException("Failed to finalize the chuncked upload, progress has stopped, tried " + tries + 1 + " times.");
}
