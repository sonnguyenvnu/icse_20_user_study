protected static PingData deserialize(final byte[] data) throws Exception {
  return Util.streamableFromByteBuffer(PingData::new,data);
}
