/** 
 * Sets all bytes in a specified block of memory to a fixed value (usually zero).
 * @param ptr   the starting memory address
 * @param value the value to set (memSet will convert it to unsigned byte)
 * @param bytes the number of bytes to set
 */
public static void memSet(long ptr,int value,long bytes){
  if (DEBUG && (ptr == NULL || bytes < 0)) {
    throw new IllegalArgumentException();
  }
  if (256L <= bytes) {
    nmemset(ptr,value,bytes);
    return;
  }
  long fill=(value & 0xFF) * FILL_PATTERN;
  int i=0, length=(int)bytes & 0xFF;
  if (length != 0) {
    int misalignment=(int)ptr & 7;
    if (misalignment != 0) {
      long aligned=ptr - misalignment;
      UNSAFE.putLong(null,aligned,merge(UNSAFE.getLong(null,aligned),fill,SHIFT.right(SHIFT.left(-1L,max(0,8 - length)),misalignment)));
      i+=8 - misalignment;
    }
  }
  for (; i <= length - 8; i+=8) {
    UNSAFE.putLong(null,ptr + i,fill);
  }
  int tail=length - i;
  if (0 < tail) {
    UNSAFE.putLong(null,ptr + i,merge(fill,UNSAFE.getLong(null,ptr + i),SHIFT.right(-1L,tail)));
  }
}
