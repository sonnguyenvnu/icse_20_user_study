public static void Init(long poly){
  IrredPoly=poly;
  int plength=72;
  long[] PowerTable=new long[plength];
  long t=One;
  for (int i=0; i < plength; i++) {
    PowerTable[i]=t;
    long mask=((t & X63) != 0) ? IrredPoly : 0;
    t=(t >>> 1) ^ mask;
  }
  ByteModTable_7=new long[256];
  for (int j=0; j <= 255; j++) {
    long v=Zero;
    for (int k=0; k <= 7; k++) {
      if ((j & (1L << k)) != 0) {
        v^=PowerTable[127 - (7 * 8) - k];
      }
    }
    ByteModTable_7[j]=v;
  }
}
