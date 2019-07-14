/** 
 * Returns the size in bytes of the given (E-)AC-3 syncframe.
 * @param data The syncframe to parse.
 * @return The syncframe size in bytes. {@link C#LENGTH_UNSET} if the input is invalid.
 */
public static int parseAc3SyncframeSize(byte[] data){
  if (data.length < 6) {
    return C.LENGTH_UNSET;
  }
  boolean isEac3=((data[5] & 0xFF) >> 3) == 16;
  if (isEac3) {
    int frmsiz=(data[2] & 0x07) << 8;
    frmsiz|=data[3] & 0xFF;
    return (frmsiz + 1) * 2;
  }
 else {
    int fscod=(data[4] & 0xC0) >> 6;
    int frmsizecod=data[4] & 0x3F;
    return getAc3SyncframeSize(fscod,frmsizecod);
  }
}
