public HashedItem createHash(long item){
  long h=(Seed64 ^ m);
  item*=m;
  item^=item >>> r;
  item*=m;
  h^=item;
  h*=m;
  fpaux.fingerprint=(byte)h;
  fpaux.fingerprint=(fpaux.fingerprint == 0L) ? 1 : fpaux.fingerprint;
  h>>>=fpSize;
  fpaux.chainId=(byte)(h & chainMask);
  h>>>=6;
  fpaux.set=(int)((h & Long.MAX_VALUE) % nrSets);
  fpaux.value=(item << 1) | 1;
  if (item == 0) {
    fpaux.value=1;
  }
  return fpaux;
}
