private int peekIntAtPosition(byte[] data,int position){
  return (data[position] & 0xFF) << 24 | (data[position + 1] & 0xFF) << 16 | (data[position + 2] & 0xFF) << 8 | (data[position + 3] & 0xFF);
}
