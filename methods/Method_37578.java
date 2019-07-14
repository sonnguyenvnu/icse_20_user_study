/** 
 * Copies  {@link InputStream} to a new {@link FastCharArrayWriter} using buffer and specified encoding.
 * @see #copy(InputStream,Writer,String,int)
 */
public static FastCharArrayWriter copy(final InputStream input,final String encoding,final int count) throws IOException {
  try (FastCharArrayWriter output=createFastCharArrayWriter()){
    copy(input,output,encoding,count);
    return output;
  }
 }
