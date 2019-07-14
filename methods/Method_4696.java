private void putNativeOrderLong(List<byte[]> initializationData,int samples){
  long ns=(samples * C.NANOS_PER_SECOND) / SAMPLE_RATE;
  byte[] array=ByteBuffer.allocate(8).order(ByteOrder.nativeOrder()).putLong(ns).array();
  initializationData.add(array);
}
