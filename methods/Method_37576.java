/** 
 * Copies  {@link InputStream} to a new {@link FastByteArrayOutputStream} using buffer and specified encoding.
 * @see #copy(InputStream,OutputStream,int)
 */
public static FastByteArrayOutputStream copyToOutputStream(final InputStream input,final int count) throws IOException {
  try (FastByteArrayOutputStream output=createFastByteArrayOutputStream()){
    copy(input,output,count);
    return output;
  }
 }
