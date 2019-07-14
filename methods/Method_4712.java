@Override protected long preparePayload(ParsableByteArray packet){
  if ((packet.data[0] & 0x01) == 1) {
    return -1;
  }
  int packetBlockSize=decodeBlockSize(packet.data[0],vorbisSetup);
  int samplesInPacket=seenFirstAudioPacket ? (packetBlockSize + previousPacketBlockSize) / 4 : 0;
  appendNumberOfSamples(packet,samplesInPacket);
  seenFirstAudioPacket=true;
  previousPacketBlockSize=packetBlockSize;
  return samplesInPacket;
}
