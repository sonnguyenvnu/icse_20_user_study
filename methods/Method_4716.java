private static int decodeBlockSize(byte firstByteOfAudioPacket,VorbisSetup vorbisSetup){
  int modeNumber=readBits(firstByteOfAudioPacket,vorbisSetup.iLogModes,1);
  int currentBlockSize;
  if (!vorbisSetup.modes[modeNumber].blockFlag) {
    currentBlockSize=vorbisSetup.idHeader.blockSize0;
  }
 else {
    currentBlockSize=vorbisSetup.idHeader.blockSize1;
  }
  return currentBlockSize;
}
