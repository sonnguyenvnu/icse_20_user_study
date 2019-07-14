private int parseAudioSpecificConfig(ParsableBitArray data) throws ParserException {
  int bitsLeft=data.bitsLeft();
  Pair<Integer,Integer> config=CodecSpecificDataUtil.parseAacAudioSpecificConfig(data,true);
  sampleRateHz=config.first;
  channelCount=config.second;
  return bitsLeft - data.bitsLeft();
}
