/** 
 * dump an array of bytes to an OutputStream
 * @param data the byte array to be dumped
 * @param offset its offset, whatever that might mean
 * @param stream the OutputStream to which the data is to bewritten
 * @param index initial index into the byte array
 * @param length number of characters to output
 * @exception IOException is thrown if anything goes wrong writingthe data to stream
 * @exception ArrayIndexOutOfBoundsException if the index isoutside the data array's bounds
 * @exception IllegalArgumentException if the output stream isnull
 */
public static void dump(final byte[] data,final long offset,final OutputStream stream,final int index,final int length) throws IOException, ArrayIndexOutOfBoundsException, IllegalArgumentException {
  if (data.length == 0) {
    stream.write(("No Data" + EOL).getBytes());
    stream.flush();
    return;
  }
  if ((index < 0) || (index >= data.length)) {
    throw new ArrayIndexOutOfBoundsException("illegal index: " + index + " into array of length " + data.length);
  }
  if (stream == null) {
    throw new IllegalArgumentException("cannot write to nullstream");
  }
  long display_offset=offset + index;
  StringBuffer buffer=new StringBuffer(74);
  int data_length=Math.min(data.length,index + length);
  for (int j=index; j < data_length; j+=16) {
    int chars_read=data_length - j;
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
    stream.write(buffer.toString().getBytes());
    stream.flush();
    buffer.setLength(0);
    display_offset+=chars_read;
  }
}
