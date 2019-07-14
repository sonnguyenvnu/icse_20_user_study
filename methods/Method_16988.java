public static int murmur3_32(int seed,int[] data,int offset,int len){
  int h1=seed;
  int off=offset;
  int end=offset + len;
  while (off < end) {
    int k1=data[off++];
    k1*=0xcc9e2d51;
    k1=Integer.rotateLeft(k1,15);
    k1*=0x1b873593;
    h1^=k1;
    h1=Integer.rotateLeft(h1,13);
    h1=h1 * 5 + 0xe6546b64;
  }
  h1^=len * (Integer.SIZE / Byte.SIZE);
  h1^=h1 >>> 16;
  h1*=0x85ebca6b;
  h1^=h1 >>> 13;
  h1*=0xc2b2ae35;
  h1^=h1 >>> 16;
  return h1;
}
