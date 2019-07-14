@Override protected long preparePayload(ParsableByteArray packet){
  if (!isAudioPacket(packet.data)) {
    return -1;
  }
  return getFlacFrameBlockSize(packet);
}
