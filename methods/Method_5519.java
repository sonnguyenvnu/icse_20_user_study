private static char getExtendedEsFrChar(byte ccData){
  int index=ccData & 0x1F;
  return (char)SPECIAL_ES_FR_CHARACTER_SET[index];
}
