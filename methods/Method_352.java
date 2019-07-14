private int getAttributes(){
  int u=header + 8 + readUnsignedShort(header + 6) * 2;
  for (int i=readUnsignedShort(u); i > 0; --i) {
    for (int j=readUnsignedShort(u + 8); j > 0; --j) {
      u+=6 + readInt(u + 12);
    }
    u+=8;
  }
  u+=2;
  for (int i=readUnsignedShort(u); i > 0; --i) {
    for (int j=readUnsignedShort(u + 8); j > 0; --j) {
      u+=6 + readInt(u + 12);
    }
    u+=8;
  }
  return u + 2;
}
