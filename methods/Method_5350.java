private void loadMedia() throws IOException, InterruptedException {
  DataSpec loadDataSpec;
  boolean skipLoadedBytes;
  if (isEncrypted) {
    loadDataSpec=dataSpec;
    skipLoadedBytes=nextLoadPosition != 0;
  }
 else {
    loadDataSpec=dataSpec.subrange(nextLoadPosition);
    skipLoadedBytes=false;
  }
  if (!isMasterTimestampSource) {
    timestampAdjuster.waitUntilInitialized();
  }
 else   if (timestampAdjuster.getFirstSampleTimestampUs() == TimestampAdjuster.DO_NOT_OFFSET) {
    timestampAdjuster.setFirstSampleTimestampUs(startTimeUs);
  }
  try {
    ExtractorInput input=prepareExtraction(dataSource,loadDataSpec);
    if (skipLoadedBytes) {
      input.skipFully(nextLoadPosition);
    }
    try {
      int result=Extractor.RESULT_CONTINUE;
      while (result == Extractor.RESULT_CONTINUE && !loadCanceled) {
        result=extractor.read(input,null);
      }
    }
  finally {
      nextLoadPosition=(int)(input.getPosition() - dataSpec.absoluteStreamPosition);
    }
  }
  finally {
    Util.closeQuietly(dataSource);
  }
}
