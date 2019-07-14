/** 
 * Returns new  {@link FastByteArrayOutputStream} using default IO buffer size.
 * @return new {@link FastByteArrayOutputStream} using default IO buffer size.
 */
private static FastByteArrayOutputStream createFastByteArrayOutputStream(){
  return new FastByteArrayOutputStream(bufferSize());
}
