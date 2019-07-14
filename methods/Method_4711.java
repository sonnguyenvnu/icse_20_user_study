@Override protected void reset(boolean headerData){
  super.reset(headerData);
  if (headerData) {
    vorbisSetup=null;
    vorbisIdHeader=null;
    commentHeader=null;
  }
  previousPacketBlockSize=0;
  seenFirstAudioPacket=false;
}
