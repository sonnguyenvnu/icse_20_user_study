private int findPitchPeriod(short[] samples,int position){
  int period;
  int retPeriod;
  int skip=inputSampleRateHz > AMDF_FREQUENCY ? inputSampleRateHz / AMDF_FREQUENCY : 1;
  if (channelCount == 1 && skip == 1) {
    period=findPitchPeriodInRange(samples,position,minPeriod,maxPeriod);
  }
 else {
    downSampleInput(samples,position,skip);
    period=findPitchPeriodInRange(downSampleBuffer,0,minPeriod / skip,maxPeriod / skip);
    if (skip != 1) {
      period*=skip;
      int minP=period - (skip * 4);
      int maxP=period + (skip * 4);
      if (minP < minPeriod) {
        minP=minPeriod;
      }
      if (maxP > maxPeriod) {
        maxP=maxPeriod;
      }
      if (channelCount == 1) {
        period=findPitchPeriodInRange(samples,position,minP,maxP);
      }
 else {
        downSampleInput(samples,position,1);
        period=findPitchPeriodInRange(downSampleBuffer,0,minP,maxP);
      }
    }
  }
  if (previousPeriodBetter(minDiff,maxDiff)) {
    retPeriod=prevPeriod;
  }
 else {
    retPeriod=period;
  }
  prevMinDiff=minDiff;
  prevPeriod=period;
  return retPeriod;
}
