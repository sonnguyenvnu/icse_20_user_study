public static int stringSize(int x){
  for (int i=0; ; i++) {
    if (x <= sizeTable[i]) {
      return i + 1;
    }
  }
}
