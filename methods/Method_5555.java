/** 
 * Parses a CLUT definition segment, as defined by ETSI EN 300 743 7.2.4.
 */
private static ClutDefinition parseClutDefinition(ParsableBitArray data,int length){
  int clutId=data.readBits(8);
  data.skipBits(8);
  int remainingLength=length - 2;
  int[] clutEntries2Bit=generateDefault2BitClutEntries();
  int[] clutEntries4Bit=generateDefault4BitClutEntries();
  int[] clutEntries8Bit=generateDefault8BitClutEntries();
  while (remainingLength > 0) {
    int entryId=data.readBits(8);
    int entryFlags=data.readBits(8);
    remainingLength-=2;
    int[] clutEntries;
    if ((entryFlags & 0x80) != 0) {
      clutEntries=clutEntries2Bit;
    }
 else     if ((entryFlags & 0x40) != 0) {
      clutEntries=clutEntries4Bit;
    }
 else {
      clutEntries=clutEntries8Bit;
    }
    int y;
    int cr;
    int cb;
    int t;
    if ((entryFlags & 0x01) != 0) {
      y=data.readBits(8);
      cr=data.readBits(8);
      cb=data.readBits(8);
      t=data.readBits(8);
      remainingLength-=4;
    }
 else {
      y=data.readBits(6) << 2;
      cr=data.readBits(4) << 4;
      cb=data.readBits(4) << 4;
      t=data.readBits(2) << 6;
      remainingLength-=2;
    }
    if (y == 0x00) {
      cr=0x00;
      cb=0x00;
      t=0xFF;
    }
    int a=(byte)(0xFF - (t & 0xFF));
    int r=(int)(y + (1.40200 * (cr - 128)));
    int g=(int)(y - (0.34414 * (cb - 128)) - (0.71414 * (cr - 128)));
    int b=(int)(y + (1.77200 * (cb - 128)));
    clutEntries[entryId]=getColor(a,Util.constrainValue(r,0,255),Util.constrainValue(g,0,255),Util.constrainValue(b,0,255));
  }
  return new ClutDefinition(clutId,clutEntries2Bit,clutEntries4Bit,clutEntries8Bit);
}
