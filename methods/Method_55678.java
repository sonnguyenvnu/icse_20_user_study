private static int strlen32NT1(long address,int maxLength){
  int i=0;
  if (4 <= maxLength) {
    int misalignment=(int)address & 3;
    if (misalignment != 0) {
      for (int len=4 - misalignment; i < len; i++) {
        if (UNSAFE.getByte(null,address + i) == 0) {
          return i;
        }
      }
    }
    while (i <= maxLength - 4) {
      if (mathHasZeroByte(UNSAFE.getInt(null,address + i))) {
        break;
      }
      i+=4;
    }
  }
  for (; i < maxLength; i++) {
    if (UNSAFE.getByte(null,address + i) == 0) {
      break;
    }
  }
  return i;
}
