private @Nullable float[] parseMetadata(ByteBuffer data){
  if (data.remaining() != 16) {
    return null;
  }
  scratch.reset(data.array(),data.limit());
  scratch.setPosition(data.arrayOffset() + 4);
  float[] result=new float[3];
  for (int i=0; i < 3; i++) {
    result[i]=Float.intBitsToFloat(scratch.readLittleEndianInt());
  }
  return result;
}
