private static int strlen64NT2(long address,int maxLength){
  int i=0;
  if (8 <= maxLength) {
    int misalignment=(int)address & 7;
    if (misalignment != 0) {
      for (int len=8 - misalignment; i < len; i+=2) {
        if (UNSAFE.getShort(null,address + i) == 0) {
          return i;
        }
      }
    }
    while (i <= maxLength - 8) {
      if (mathHasZeroShort(UNSAFE.getLong(null,address + i))) {
        break;
      }
      i+=8;
    }
  }
  for (; i < maxLength; i+=2) {
    if (UNSAFE.getShort(null,address + i) == 0) {
      break;
    }
  }
  return i;
}
