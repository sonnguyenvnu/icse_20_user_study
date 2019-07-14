private static int strlen32NT2(long address,int maxLength){
  int i=0;
  if (4 <= maxLength) {
    int misalignment=(int)address & 3;
    if (misalignment != 0) {
      for (int len=4 - misalignment; i < len; i+=2) {
        if (UNSAFE.getShort(null,address + i) == 0) {
          return i;
        }
      }
    }
    while (i <= maxLength - 4) {
      if (mathHasZeroShort(UNSAFE.getInt(null,address + i))) {
        break;
      }
      i+=4;
    }
  }
  for (; i < maxLength; i+=2) {
    if (UNSAFE.getShort(null,address + i) == 0) {
      break;
    }
  }
  return i;
}
