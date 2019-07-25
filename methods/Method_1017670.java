/** 
 * Uncompress the content in the input buffer. The uncompressed data is written to the output buffer. <p/> Note that if you pass the wrong data or the range [inputOffset, inputOffset + inputLength) that cannot be uncompressed, your JVM might crash due to the access violation exception issued in the native code written in C++. To avoid this type of crash, use {@link #isValidCompressedBuffer(byte[],int,int)} first.
 * @param input
 * @param inputOffset
 * @param inputLength
 * @param output
 * @param outputOffset
 * @return the byte size of the uncompressed data
 * @throws IOException
 */
public static int uncompress(byte[] input,int inputOffset,int inputLength,byte[] output,int outputOffset) throws IOException {
  return rawUncompress(input,inputOffset,inputLength,output,outputOffset);
}
