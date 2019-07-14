private boolean shouldConsumePacketPayload(int packetPid){
  return mode == MODE_HLS || tracksEnded || !trackPids.get(packetPid,false);
}
