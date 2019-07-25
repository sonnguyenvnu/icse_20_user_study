@Setup public void setup(){
  Random r=new Random();
  final int N=1024;
  bitmap=new long[N];
  key=new int[N];
  for (int k=0; k < bitmap.length; ++k) {
    while (bitmap[k] == 0)     bitmap[k]=r.nextInt();
  }
  for (int k=0; k < key.length; ++k)   key[k]=r.nextInt() % (Long.bitCount(bitmap[k]));
  for (int k=0; k < 64; ++k) {
    if (select(0xFFFFFFFFFFFFFFFFL,k) != k)     throw new RuntimeException("bug " + k);
  }
  for (int k=0; k < 64; ++k) {
    if (selectBitPosition(0xFFFFFFFFFFFFFFFFL,k) != k)     throw new RuntimeException("bug " + k);
  }
  for (int k=0; k < bitmap.length; ++k)   if (selectBitPosition(bitmap[k],key[k]) != select(bitmap[k],key[k]))   throw new RuntimeException("bug " + bitmap[k] + " " + key[k] + " : " + selectBitPosition(bitmap[k],key[k]) + " : " + select(bitmap[k],key[k]));
}
