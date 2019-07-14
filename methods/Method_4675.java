/** 
 * Parses  {@code headerData}, populating  {@code header} with the parsed data.
 * @param headerData Header data to parse.
 * @param header Header to populate with data from {@code headerData}.
 * @return True if the header was populated. False otherwise, indicating that {@code headerData}is not a valid MPEG audio header.
 */
public static boolean populateHeader(int headerData,MpegAudioHeader header){
  if ((headerData & 0xFFE00000) != 0xFFE00000) {
    return false;
  }
  int version=(headerData >>> 19) & 3;
  if (version == 1) {
    return false;
  }
  int layer=(headerData >>> 17) & 3;
  if (layer == 0) {
    return false;
  }
  int bitrateIndex=(headerData >>> 12) & 15;
  if (bitrateIndex == 0 || bitrateIndex == 0xF) {
    return false;
  }
  int samplingRateIndex=(headerData >>> 10) & 3;
  if (samplingRateIndex == 3) {
    return false;
  }
  int sampleRate=SAMPLING_RATE_V1[samplingRateIndex];
  if (version == 2) {
    sampleRate/=2;
  }
 else   if (version == 0) {
    sampleRate/=4;
  }
  int padding=(headerData >>> 9) & 1;
  int bitrate;
  int frameSize;
  int samplesPerFrame;
  if (layer == 3) {
    bitrate=version == 3 ? BITRATE_V1_L1[bitrateIndex - 1] : BITRATE_V2_L1[bitrateIndex - 1];
    frameSize=(12 * bitrate / sampleRate + padding) * 4;
    samplesPerFrame=384;
  }
 else {
    if (version == 3) {
      bitrate=layer == 2 ? BITRATE_V1_L2[bitrateIndex - 1] : BITRATE_V1_L3[bitrateIndex - 1];
      samplesPerFrame=1152;
      frameSize=144 * bitrate / sampleRate + padding;
    }
 else {
      bitrate=BITRATE_V2[bitrateIndex - 1];
      samplesPerFrame=layer == 1 ? 576 : 1152;
      frameSize=(layer == 1 ? 72 : 144) * bitrate / sampleRate + padding;
    }
  }
  bitrate=8 * frameSize * sampleRate / samplesPerFrame;
  String mimeType=MIME_TYPE_BY_LAYER[3 - layer];
  int channels=((headerData >> 6) & 3) == 3 ? 1 : 2;
  header.setValues(version,mimeType,frameSize,sampleRate,channels,bitrate,samplesPerFrame);
  return true;
}
