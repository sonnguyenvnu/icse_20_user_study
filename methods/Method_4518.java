private void readPastStreamInfo(ExtractorInput input) throws InterruptedException, IOException {
  if (readPastStreamInfo) {
    return;
  }
  FlacStreamInfo streamInfo=decodeStreamInfo(input);
  readPastStreamInfo=true;
  if (this.streamInfo == null) {
    updateFlacStreamInfo(input,streamInfo);
  }
}
