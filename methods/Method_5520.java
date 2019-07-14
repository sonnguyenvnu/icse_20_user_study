private static char getExtendedPtDeChar(byte ccData){
  int index=ccData & 0x1F;
  return (char)SPECIAL_PT_DE_CHARACTER_SET[index];
}
