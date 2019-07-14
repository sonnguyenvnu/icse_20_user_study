@Override protected float getCodecOperatingRateV23(float operatingRate,Format format,Format[] streamFormats){
  int maxSampleRate=-1;
  for (  Format streamFormat : streamFormats) {
    int streamSampleRate=streamFormat.sampleRate;
    if (streamSampleRate != Format.NO_VALUE) {
      maxSampleRate=Math.max(maxSampleRate,streamSampleRate);
    }
  }
  return maxSampleRate == -1 ? CODEC_OPERATING_RATE_UNSET : (maxSampleRate * operatingRate);
}
