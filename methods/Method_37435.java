/** 
 * Returns the MurmurHash3_x64_128 hash.
 */
public static HashValue murmurhash3_x64_128(final byte[] key,final int offset,final int len,final int seed){
  long h1=seed & 0x00000000FFFFFFFFL;
  long h2=seed & 0x00000000FFFFFFFFL;
  final long c1=0x87c37b91114253d5L;
  final long c2=0x4cf5ad432745937fL;
  int roundedEnd=offset + (len & 0xFFFFFFF0);
  for (int i=offset; i < roundedEnd; i+=16) {
    long k1=getLongLittleEndian(key,i);
    long k2=getLongLittleEndian(key,i + 8);
    k1*=c1;
    k1=Long.rotateLeft(k1,31);
    k1*=c2;
    h1^=k1;
    h1=Long.rotateLeft(h1,27);
    h1+=h2;
    h1=h1 * 5 + 0x52dce729;
    k2*=c2;
    k2=Long.rotateLeft(k2,33);
    k2*=c1;
    h2^=k2;
    h2=Long.rotateLeft(h2,31);
    h2+=h1;
    h2=h2 * 5 + 0x38495ab5;
  }
  long k1=0;
  long k2=0;
switch (len & 15) {
case 15:
    k2=(key[roundedEnd + 14] & 0xffL) << 48;
case 14:
  k2|=(key[roundedEnd + 13] & 0xffL) << 40;
case 13:
k2|=(key[roundedEnd + 12] & 0xffL) << 32;
case 12:
k2|=(key[roundedEnd + 11] & 0xffL) << 24;
case 11:
k2|=(key[roundedEnd + 10] & 0xffL) << 16;
case 10:
k2|=(key[roundedEnd + 9] & 0xffL) << 8;
case 9:
k2|=(key[roundedEnd + 8] & 0xffL);
k2*=c2;
k2=Long.rotateLeft(k2,33);
k2*=c1;
h2^=k2;
case 8:
k1=((long)key[roundedEnd + 7]) << 56;
case 7:
k1|=(key[roundedEnd + 6] & 0xffL) << 48;
case 6:
k1|=(key[roundedEnd + 5] & 0xffL) << 40;
case 5:
k1|=(key[roundedEnd + 4] & 0xffL) << 32;
case 4:
k1|=(key[roundedEnd + 3] & 0xffL) << 24;
case 3:
k1|=(key[roundedEnd + 2] & 0xffL) << 16;
case 2:
k1|=(key[roundedEnd + 1] & 0xffL) << 8;
case 1:
k1|=(key[roundedEnd] & 0xffL);
k1*=c1;
k1=Long.rotateLeft(k1,31);
k1*=c2;
h1^=k1;
}
h1^=len;
h2^=len;
h1+=h2;
h2+=h1;
h1=fmix64(h1);
h2=fmix64(h2);
h1+=h2;
h2+=h1;
return new HashValue(h1,h2);
}
