@Override public long loadLong(byte[] data,int index){
  return (data[index] & 0xffL) | (data[index + 1] & 0xffL) << 8 | (data[index + 2] & 0xffL) << 16 | (data[index + 3] & 0xffL) << 24 | (data[index + 4] & 0xffL) << 32 | (data[index + 5] & 0xffL) << 40 | (data[index + 6] & 0xffL) << 48 | (data[index + 7] & 0xffL) << 56;
}
