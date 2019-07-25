private static void decode(byte[] data,int[] freq,int[] cumulativeFreq,byte[] freqToCode,byte[] out){
  ByteBuffer buff=ByteBuffer.wrap(data);
  long state=buff.getLong();
  for (int i=0, size=out.length; i < size; i++) {
    int x=(int)state & MASK;
    int c=freqToCode[x] & 0xff;
    out[i]=(byte)c;
    state=(freq[c] * (state >> SHIFT)) + x - cumulativeFreq[c];
    while (state < TOP) {
      state=(state << 32) | (buff.getInt() & 0xffffffffL);
    }
  }
}
