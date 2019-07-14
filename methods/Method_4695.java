@Override protected long preparePayload(ParsableByteArray packet){
  return convertTimeToGranule(getPacketDurationUs(packet.data));
}
