protected final int seekToPosition(ExtractorInput input,long position,PositionHolder seekPositionHolder){
  if (position == input.getPosition()) {
    return Extractor.RESULT_CONTINUE;
  }
 else {
    seekPositionHolder.position=position;
    return Extractor.RESULT_SEEK;
  }
}
