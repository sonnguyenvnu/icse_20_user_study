private void maybeOutputSeekMap(long inputLength){
  if (!hasOutputSeekMap) {
    hasOutputSeekMap=true;
    if (durationReader.getDurationUs() != C.TIME_UNSET) {
      tsBinarySearchSeeker=new TsBinarySearchSeeker(durationReader.getPcrTimestampAdjuster(),durationReader.getDurationUs(),inputLength,pcrPid);
      output.seekMap(tsBinarySearchSeeker.getSeekMap());
    }
 else {
      output.seekMap(new SeekMap.Unseekable(durationReader.getDurationUs()));
    }
  }
}
