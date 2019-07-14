private static byte[] allocateBytes(int length){
  if (length > BYTES_CACH_MAX_SIZE) {
    return new byte[length];
  }
  int allocateLength=getAllocateLengthExp(BYTES_CACH_INIT_SIZE_EXP,BYTES_CACH_MAX_SIZE_EXP,length);
  byte[] chars=new byte[allocateLength];
  bytesBufLocal.set(new SoftReference<byte[]>(chars));
  return chars;
}
