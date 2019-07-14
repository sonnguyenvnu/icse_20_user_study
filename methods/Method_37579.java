/** 
 * Copies  {@link Reader} to a new {@link FastCharArrayWriter} using buffer and specified encoding.
 * @see #copy(Reader,Writer,int)
 */
public static FastCharArrayWriter copy(final Reader input,final int count) throws IOException {
  try (FastCharArrayWriter output=createFastCharArrayWriter()){
    copy(input,output,count);
    return output;
  }
 }
