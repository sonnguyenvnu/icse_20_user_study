private static int hash(int x,int level,int offset,int size){
  x+=level + offset * 32;
  x=((x >>> 16) ^ x) * 0x45d9f3b;
  x=((x >>> 16) ^ x) * 0x45d9f3b;
  x=(x >>> 16) ^ x;
  return (x & (-1 >>> 1)) % size;
}
