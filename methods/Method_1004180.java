public long value(long entry){
  return (entry >>> keyBits) & valueMask;
}
