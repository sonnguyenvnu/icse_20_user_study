public long entry(long key,long value){
  return key | (value << keyBits);
}
