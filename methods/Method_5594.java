private FrameAndTickRate parseFrameAndTickRates(XmlPullParser xmlParser) throws SubtitleDecoderException {
  int frameRate=DEFAULT_FRAME_RATE;
  String frameRateString=xmlParser.getAttributeValue(TTP,"frameRate");
  if (frameRateString != null) {
    frameRate=Integer.parseInt(frameRateString);
  }
  float frameRateMultiplier=1;
  String frameRateMultiplierString=xmlParser.getAttributeValue(TTP,"frameRateMultiplier");
  if (frameRateMultiplierString != null) {
    String[] parts=Util.split(frameRateMultiplierString," ");
    if (parts.length != 2) {
      throw new SubtitleDecoderException("frameRateMultiplier doesn't have 2 parts");
    }
    float numerator=Integer.parseInt(parts[0]);
    float denominator=Integer.parseInt(parts[1]);
    frameRateMultiplier=numerator / denominator;
  }
  int subFrameRate=DEFAULT_FRAME_AND_TICK_RATE.subFrameRate;
  String subFrameRateString=xmlParser.getAttributeValue(TTP,"subFrameRate");
  if (subFrameRateString != null) {
    subFrameRate=Integer.parseInt(subFrameRateString);
  }
  int tickRate=DEFAULT_FRAME_AND_TICK_RATE.tickRate;
  String tickRateString=xmlParser.getAttributeValue(TTP,"tickRate");
  if (tickRateString != null) {
    tickRate=Integer.parseInt(tickRateString);
  }
  return new FrameAndTickRate(frameRate * frameRateMultiplier,subFrameRate,tickRate);
}
