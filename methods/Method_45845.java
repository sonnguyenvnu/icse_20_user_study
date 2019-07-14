@Override public int loadInt(byte[] data,int index){
  return (data[index] & 0xff) | (data[index + 1] & 0xff) << 8 | (data[index + 2] & 0xff) << 16 | (data[index + 3] & 0xff) << 24;
}
