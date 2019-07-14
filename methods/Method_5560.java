/** 
 * Draws a pixel data sub-block, as defined by ETSI EN 300 743 7.2.5.1, into a canvas.
 */
private static void paintPixelDataSubBlock(byte[] pixelData,int[] clutEntries,int regionDepth,int horizontalAddress,int verticalAddress,Paint paint,Canvas canvas){
  ParsableBitArray data=new ParsableBitArray(pixelData);
  int column=horizontalAddress;
  int line=verticalAddress;
  byte[] clutMapTable2To4=null;
  byte[] clutMapTable2To8=null;
  byte[] clutMapTable4To8=null;
  while (data.bitsLeft() != 0) {
    int dataType=data.readBits(8);
switch (dataType) {
case DATA_TYPE_2BP_CODE_STRING:
      byte[] clutMapTable2ToX;
    if (regionDepth == REGION_DEPTH_8_BIT) {
      clutMapTable2ToX=clutMapTable2To8 == null ? defaultMap2To8 : clutMapTable2To8;
    }
 else     if (regionDepth == REGION_DEPTH_4_BIT) {
      clutMapTable2ToX=clutMapTable2To4 == null ? defaultMap2To4 : clutMapTable2To4;
    }
 else {
      clutMapTable2ToX=null;
    }
  column=paint2BitPixelCodeString(data,clutEntries,clutMapTable2ToX,column,line,paint,canvas);
data.byteAlign();
break;
case DATA_TYPE_4BP_CODE_STRING:
byte[] clutMapTable4ToX;
if (regionDepth == REGION_DEPTH_8_BIT) {
clutMapTable4ToX=clutMapTable4To8 == null ? defaultMap4To8 : clutMapTable4To8;
}
 else {
clutMapTable4ToX=null;
}
column=paint4BitPixelCodeString(data,clutEntries,clutMapTable4ToX,column,line,paint,canvas);
data.byteAlign();
break;
case DATA_TYPE_8BP_CODE_STRING:
column=paint8BitPixelCodeString(data,clutEntries,null,column,line,paint,canvas);
break;
case DATA_TYPE_24_TABLE_DATA:
clutMapTable2To4=buildClutMapTable(4,4,data);
break;
case DATA_TYPE_28_TABLE_DATA:
clutMapTable2To8=buildClutMapTable(4,8,data);
break;
case DATA_TYPE_48_TABLE_DATA:
clutMapTable2To8=buildClutMapTable(16,8,data);
break;
case DATA_TYPE_END_LINE:
column=horizontalAddress;
line+=2;
break;
default :
break;
}
}
}
