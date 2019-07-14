/** 
 * Reads all bytes from inputStream and writes them to outputStream. When all bytes are read outputStream.toByteBuffer is called and obtained MemoryPooledByteBuffer is returned
 * @param inputStream the input stream to read from
 * @param outputStream output stream used to transform content of input stream toMemoryPooledByteBuffer
 * @return an instance of MemoryPooledByteBuffer
 * @throws IOException
 */
@VisibleForTesting MemoryPooledByteBuffer newByteBuf(InputStream inputStream,MemoryPooledByteBufferOutputStream outputStream) throws IOException {
  mPooledByteStreams.copy(inputStream,outputStream);
  return outputStream.toByteBuffer();
}
