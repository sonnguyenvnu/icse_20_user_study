private void maybeOutputSeekMap(long inputLength,int sampleReadResult){
  if (hasOutputSeekMap) {
    return;
  }
  if ((flags & FLAG_ENABLE_CONSTANT_BITRATE_SEEKING) == 0 || inputLength == C.LENGTH_UNSET || (firstSampleSize != C.LENGTH_UNSET && firstSampleSize != currentSampleSize)) {
    seekMap=new SeekMap.Unseekable(C.TIME_UNSET);
    extractorOutput.seekMap(seekMap);
    hasOutputSeekMap=true;
  }
 else   if (numSamplesWithSameSize >= NUM_SAME_SIZE_CONSTANT_BIT_RATE_THRESHOLD || sampleReadResult == RESULT_END_OF_INPUT) {
    seekMap=getConstantBitrateSeekMap(inputLength);
    extractorOutput.seekMap(seekMap);
    hasOutputSeekMap=true;
  }
}
