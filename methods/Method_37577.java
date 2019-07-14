/** 
 * Copies  {@link Reader} to a new {@link FastByteArrayOutputStream} using buffer and specified encoding.
 * @see #copy(Reader,OutputStream,String,int)
 */
public static FastByteArrayOutputStream copyToOutputStream(final Reader input,final String encoding,final int count) throws IOException {
  try (FastByteArrayOutputStream output=createFastByteArrayOutputStream()){
    copy(input,output,encoding,count);
    return output;
  }
 }
