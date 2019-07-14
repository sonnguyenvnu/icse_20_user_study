@Override protected float getCodecOperatingRateV23(float operatingRate,Format format,Format[] streamFormats){
  float maxFrameRate=-1;
  for (  Format streamFormat : streamFormats) {
    float streamFrameRate=streamFormat.frameRate;
    if (streamFrameRate != Format.NO_VALUE) {
      maxFrameRate=Math.max(maxFrameRate,streamFrameRate);
    }
  }
  return maxFrameRate == -1 ? CODEC_OPERATING_RATE_UNSET : (maxFrameRate * operatingRate);
}
