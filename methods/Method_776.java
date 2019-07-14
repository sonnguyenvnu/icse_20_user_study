private static char[] allocate(int length){
  if (length > CHARS_CACH_MAX_SIZE) {
    return new char[length];
  }
  int allocateLength=getAllocateLengthExp(CHARS_CACH_INIT_SIZE_EXP,CHARS_CACH_MAX_SIZE_EXP,length);
  char[] chars=new char[allocateLength];
  charsBufLocal.set(new SoftReference<char[]>(chars));
  return chars;
}
