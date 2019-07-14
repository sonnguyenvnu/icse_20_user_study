private static int[] generateDefault4BitClutEntries(){
  int[] entries=new int[16];
  entries[0]=0x00000000;
  for (int i=1; i < entries.length; i++) {
    if (i < 8) {
      entries[i]=getColor(0xFF,((i & 0x01) != 0 ? 0xFF : 0x00),((i & 0x02) != 0 ? 0xFF : 0x00),((i & 0x04) != 0 ? 0xFF : 0x00));
    }
 else {
      entries[i]=getColor(0xFF,((i & 0x01) != 0 ? 0x7F : 0x00),((i & 0x02) != 0 ? 0x7F : 0x00),((i & 0x04) != 0 ? 0x7F : 0x00));
    }
  }
  return entries;
}
