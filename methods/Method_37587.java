/** 
 * Returns new  {@link FastCharArrayWriter} using default IO buffer size.
 * @return new {@link FastCharArrayWriter} using default IO buffer size.
 */
private static FastCharArrayWriter createFastCharArrayWriter(){
  return new FastCharArrayWriter(bufferSize());
}
