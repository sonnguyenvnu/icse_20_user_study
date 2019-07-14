/** 
 * Parses the sample header.
 */
private void parseAdtsHeader() throws ParserException {
  adtsScratch.setPosition(0);
  if (!hasOutputFormat) {
    int audioObjectType=adtsScratch.readBits(2) + 1;
    if (audioObjectType != 2) {
      Log.w(TAG,"Detected audio object type: " + audioObjectType + ", but assuming AAC LC.");
      audioObjectType=2;
    }
    adtsScratch.skipBits(5);
    int channelConfig=adtsScratch.readBits(3);
    byte[] audioSpecificConfig=CodecSpecificDataUtil.buildAacAudioSpecificConfig(audioObjectType,firstFrameSampleRateIndex,channelConfig);
    Pair<Integer,Integer> audioParams=CodecSpecificDataUtil.parseAacAudioSpecificConfig(audioSpecificConfig);
    Format format=Format.createAudioSampleFormat(formatId,MimeTypes.AUDIO_AAC,null,Format.NO_VALUE,Format.NO_VALUE,audioParams.second,audioParams.first,Collections.singletonList(audioSpecificConfig),null,0,language);
    sampleDurationUs=(C.MICROS_PER_SECOND * 1024) / format.sampleRate;
    output.format(format);
    hasOutputFormat=true;
  }
 else {
    adtsScratch.skipBits(10);
  }
  adtsScratch.skipBits(4);
  int sampleSize=adtsScratch.readBits(13) - 2 - HEADER_SIZE;
  if (hasCrc) {
    sampleSize-=CRC_SIZE;
  }
  setReadingSampleState(output,sampleDurationUs,0,sampleSize);
}
