/** 
 * Returns the size of the frame associated with  {@code header}, or  {@link C#LENGTH_UNSET} if itis invalid.
 */
public static int getFrameSize(int header){
  if ((header & 0xFFE00000) != 0xFFE00000) {
    return C.LENGTH_UNSET;
  }
  int version=(header >>> 19) & 3;
  if (version == 1) {
    return C.LENGTH_UNSET;
  }
  int layer=(header >>> 17) & 3;
  if (layer == 0) {
    return C.LENGTH_UNSET;
  }
  int bitrateIndex=(header >>> 12) & 15;
  if (bitrateIndex == 0 || bitrateIndex == 0xF) {
    return C.LENGTH_UNSET;
  }
  int samplingRateIndex=(header >>> 10) & 3;
  if (samplingRateIndex == 3) {
    return C.LENGTH_UNSET;
  }
  int samplingRate=SAMPLING_RATE_V1[samplingRateIndex];
  if (version == 2) {
    samplingRate/=2;
  }
 else   if (version == 0) {
    samplingRate/=4;
  }
  int bitrate;
  int padding=(header >>> 9) & 1;
  if (layer == 3) {
    bitrate=version == 3 ? BITRATE_V1_L1[bitrateIndex - 1] : BITRATE_V2_L1[bitrateIndex - 1];
    return (12 * bitrate / samplingRate + padding) * 4;
  }
 else {
    if (version == 3) {
      bitrate=layer == 2 ? BITRATE_V1_L2[bitrateIndex - 1] : BITRATE_V1_L3[bitrateIndex - 1];
    }
 else {
      bitrate=BITRATE_V2[bitrateIndex - 1];
    }
  }
  if (version == 3) {
    return 144 * bitrate / samplingRate + padding;
  }
 else {
    return (layer == 1 ? 72 : 144) * bitrate / samplingRate + padding;
  }
}
