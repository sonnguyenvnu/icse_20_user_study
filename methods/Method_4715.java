@VisibleForTesting static void appendNumberOfSamples(ParsableByteArray buffer,long packetSampleCount){
  buffer.setLimit(buffer.limit() + 4);
  buffer.data[buffer.limit() - 4]=(byte)(packetSampleCount & 0xFF);
  buffer.data[buffer.limit() - 3]=(byte)((packetSampleCount >>> 8) & 0xFF);
  buffer.data[buffer.limit() - 2]=(byte)((packetSampleCount >>> 16) & 0xFF);
  buffer.data[buffer.limit() - 1]=(byte)((packetSampleCount >>> 24) & 0xFF);
}
