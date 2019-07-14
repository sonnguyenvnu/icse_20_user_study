private static void setSampleDuration(byte[] subripSampleData,long durationUs,String timecodeFormat,int endTimecodeOffset,long lastTimecodeValueScalingFactor,byte[] emptyTimecode){
  byte[] timeCodeData;
  if (durationUs == C.TIME_UNSET) {
    timeCodeData=emptyTimecode;
  }
 else {
    int hours=(int)(durationUs / (3600 * C.MICROS_PER_SECOND));
    durationUs-=(hours * 3600 * C.MICROS_PER_SECOND);
    int minutes=(int)(durationUs / (60 * C.MICROS_PER_SECOND));
    durationUs-=(minutes * 60 * C.MICROS_PER_SECOND);
    int seconds=(int)(durationUs / C.MICROS_PER_SECOND);
    durationUs-=(seconds * C.MICROS_PER_SECOND);
    int lastValue=(int)(durationUs / lastTimecodeValueScalingFactor);
    timeCodeData=Util.getUtf8Bytes(String.format(Locale.US,timecodeFormat,hours,minutes,seconds,lastValue));
  }
  System.arraycopy(timeCodeData,0,subripSampleData,endTimecodeOffset,emptyTimecode.length);
}
