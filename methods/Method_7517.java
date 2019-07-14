public void writeByteBuffer(NativeByteBuffer b){
  try {
    int l=b.limit();
    if (l <= 253) {
      if (!justCalc) {
        buffer.put((byte)l);
      }
 else {
        len+=1;
      }
    }
 else {
      if (!justCalc) {
        buffer.put((byte)254);
        buffer.put((byte)l);
        buffer.put((byte)(l >> 8));
        buffer.put((byte)(l >> 16));
      }
 else {
        len+=4;
      }
    }
    if (!justCalc) {
      b.rewind();
      buffer.put(b.buffer);
    }
 else {
      len+=l;
    }
    int i=l <= 253 ? 1 : 4;
    while ((l + i) % 4 != 0) {
      if (!justCalc) {
        buffer.put((byte)0);
      }
 else {
        len+=1;
      }
      i++;
    }
  }
 catch (  Exception e) {
    FileLog.e(e);
  }
}
