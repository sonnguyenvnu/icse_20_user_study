private static int emitCopyLessThan64(byte[] output,int outputIndex,int offset,int length){
  assert offset >= 0;
  assert length <= 64;
  assert length >= 4;
  assert offset < 65536;
  if ((length < 12) && (offset < 2048)) {
    int lenMinus4=length - 4;
    assert (lenMinus4 < 8);
    output[outputIndex++]=(byte)(COPY_1_BYTE_OFFSET | ((lenMinus4) << 2) | ((offset >>> 8) << 5));
    output[outputIndex++]=(byte)(offset);
  }
 else {
    output[outputIndex++]=(byte)(COPY_2_BYTE_OFFSET | ((length - 1) << 2));
    output[outputIndex++]=(byte)(offset);
    output[outputIndex++]=(byte)(offset >>> 8);
  }
  return outputIndex;
}
