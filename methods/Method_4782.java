/** 
 * Parses a StreamMuxConfig as defined in ISO/IEC 14496-3:2009 Section 1.7.3.1, Table 1.42.
 */
private void parseStreamMuxConfig(ParsableBitArray data) throws ParserException {
  int audioMuxVersion=data.readBits(1);
  audioMuxVersionA=audioMuxVersion == 1 ? data.readBits(1) : 0;
  if (audioMuxVersionA == 0) {
    if (audioMuxVersion == 1) {
      latmGetValue(data);
    }
    if (!data.readBit()) {
      throw new ParserException();
    }
    numSubframes=data.readBits(6);
    int numProgram=data.readBits(4);
    int numLayer=data.readBits(3);
    if (numProgram != 0 || numLayer != 0) {
      throw new ParserException();
    }
    if (audioMuxVersion == 0) {
      int startPosition=data.getPosition();
      int readBits=parseAudioSpecificConfig(data);
      data.setPosition(startPosition);
      byte[] initData=new byte[(readBits + 7) / 8];
      data.readBits(initData,0,readBits);
      Format format=Format.createAudioSampleFormat(formatId,MimeTypes.AUDIO_AAC,null,Format.NO_VALUE,Format.NO_VALUE,channelCount,sampleRateHz,Collections.singletonList(initData),null,0,language);
      if (!format.equals(this.format)) {
        this.format=format;
        sampleDurationUs=(C.MICROS_PER_SECOND * 1024) / format.sampleRate;
        output.format(format);
      }
    }
 else {
      int ascLen=(int)latmGetValue(data);
      int bitsRead=parseAudioSpecificConfig(data);
      data.skipBits(ascLen - bitsRead);
    }
    parseFrameLength(data);
    otherDataPresent=data.readBit();
    otherDataLenBits=0;
    if (otherDataPresent) {
      if (audioMuxVersion == 1) {
        otherDataLenBits=latmGetValue(data);
      }
 else {
        boolean otherDataLenEsc;
        do {
          otherDataLenEsc=data.readBit();
          otherDataLenBits=(otherDataLenBits << 8) + data.readBits(8);
        }
 while (otherDataLenEsc);
      }
    }
    boolean crcCheckPresent=data.readBit();
    if (crcCheckPresent) {
      data.skipBits(8);
    }
  }
 else {
    throw new ParserException();
  }
}
