/** 
 * For hbase 0.98
 * @return serialized byte array
 */
@Override public byte[] _XXXXX_(){
  ByteArrayDataOutput byteArrayDataOutput=ByteStreams.newDataOutput();
  try {
    this.write(byteArrayDataOutput);
    return byteArrayDataOutput.toByteArray();
  }
 catch (  IOException e) {
    LOG.error("Failed to serialize due to: " + e.getMessage(),e);
    throw new RuntimeException(e);
  }
}