@Override protected void parsePayload(ParsableByteArray data,long timeUs) throws ParserException {
  if (audioFormat == AUDIO_FORMAT_MP3) {
    int sampleSize=data.bytesLeft();
    output.sampleData(data,sampleSize);
    output.sampleMetadata(timeUs,C.BUFFER_FLAG_KEY_FRAME,sampleSize,0,null);
  }
 else {
    int packetType=data.readUnsignedByte();
    if (packetType == AAC_PACKET_TYPE_SEQUENCE_HEADER && !hasOutputFormat) {
      byte[] audioSpecificConfig=new byte[data.bytesLeft()];
      data.readBytes(audioSpecificConfig,0,audioSpecificConfig.length);
      Pair<Integer,Integer> audioParams=CodecSpecificDataUtil.parseAacAudioSpecificConfig(audioSpecificConfig);
      Format format=Format.createAudioSampleFormat(null,MimeTypes.AUDIO_AAC,null,Format.NO_VALUE,Format.NO_VALUE,audioParams.second,audioParams.first,Collections.singletonList(audioSpecificConfig),null,0,null);
      output.format(format);
      hasOutputFormat=true;
    }
 else     if (audioFormat != AUDIO_FORMAT_AAC || packetType == AAC_PACKET_TYPE_AAC_RAW) {
      int sampleSize=data.bytesLeft();
      output.sampleData(data,sampleSize);
      output.sampleMetadata(timeUs,C.BUFFER_FLAG_KEY_FRAME,sampleSize,0,null);
    }
  }
}
