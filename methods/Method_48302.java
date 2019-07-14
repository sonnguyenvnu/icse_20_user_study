private boolean equalKey(StaticBuffer concat,StaticBuffer key){
  int keyLength=getKeyLength(concat);
  for (int i=0; i < keyLength; i++)   if (concat.getByte(i) != key.getByte(i))   return false;
  return true;
}
