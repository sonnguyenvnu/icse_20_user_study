/** 
 * Returns the size in bytes of the given DTS frame.
 * @param data The frame to parse.
 * @return The frame's size in bytes.
 */
public static int getDtsFrameSize(byte[] data){
  int fsize;
  boolean uses14BitPerWord=false;
switch (data[0]) {
case FIRST_BYTE_14B_BE:
    fsize=(((data[6] & 0x03) << 12) | ((data[7] & 0xFF) << 4) | ((data[8] & 0x3C) >> 2)) + 1;
  uses14BitPerWord=true;
break;
case FIRST_BYTE_LE:
fsize=(((data[4] & 0x03) << 12) | ((data[7] & 0xFF) << 4) | ((data[6] & 0xF0) >> 4)) + 1;
break;
case FIRST_BYTE_14B_LE:
fsize=(((data[7] & 0x03) << 12) | ((data[6] & 0xFF) << 4) | ((data[9] & 0x3C) >> 2)) + 1;
uses14BitPerWord=true;
break;
default :
fsize=(((data[5] & 0x03) << 12) | ((data[6] & 0xFF) << 4) | ((data[7] & 0xF0) >> 4)) + 1;
}
return uses14BitPerWord ? fsize * 16 / 14 : fsize;
}
