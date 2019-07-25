/** 
 * dump an array of bytes to a String
 * @param data the byte array to be dumped
 * @param offset its offset, whatever that might mean
 * @param index initial index into the byte array
 * @exception ArrayIndexOutOfBoundsException if the index isoutside the data array's bounds
 * @return output string
 */
public static String dump(final byte[] data,final long offset,final int index){
  StringBuffer buffer;
  if ((index < 0) || (index >= data.length)) {
    throw new ArrayIndexOutOfBoundsException("illegal index: " + index + " into array of length " + data.length);
  }
  long display_offset=offset + index;
  buffer=new StringBuffer(74);
  for (int j=index; j < data.length; j+=16) {
    int chars_read=data.length - j;
    if (chars_read > 16) {
      chars_read=16;
    }
    buffer.append(dump(display_offset)).append(' ');
    for (int k=0; k < 16; k++) {
      if (k < chars_read) {
        buffer.append(dump(data[k + j]));
      }
 else {
        buffer.append("  ");
      }
      buffer.append(' ');
    }
    for (int k=0; k < chars_read; k++) {
      if ((data[k + j] >= ' ') && (data[k + j] < 127)) {
        buffer.append((char)data[k + j]);
      }
 else {
        buffer.append('.');
      }
    }
    buffer.append(EOL);
    display_offset+=chars_read;
  }
  return buffer.toString();
}
