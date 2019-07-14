/** 
 * Parses 4 characters and returns unicode character.
 */
protected char parseUnicode(){
  int i0=CharUtil.hex2int(input[ndx++]);
  int i1=CharUtil.hex2int(input[ndx++]);
  int i2=CharUtil.hex2int(input[ndx++]);
  int i3=CharUtil.hex2int(input[ndx]);
  return (char)((i0 << 12) + (i1 << 8) + (i2 << 4) + i3);
}
