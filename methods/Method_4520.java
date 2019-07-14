private SeekMap getSeekMapForNonSeekTableFlac(ExtractorInput input,FlacStreamInfo streamInfo){
  long inputLength=input.getLength();
  if (inputLength != C.LENGTH_UNSET) {
    long firstFramePosition=decoderJni.getDecodePosition();
    flacBinarySearchSeeker=new FlacBinarySearchSeeker(streamInfo,firstFramePosition,inputLength,decoderJni);
    return flacBinarySearchSeeker.getSeekMap();
  }
 else {
    return new SeekMap.Unseekable(streamInfo.durationUs());
  }
}
