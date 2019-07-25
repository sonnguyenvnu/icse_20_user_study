/** 
 * Read the contents of a file. <p> Use  {@link #open(Path,Set,FileAttribute[])} to create a file promise.<p> The file channel is closed on success or failure.
 * @param file a promise for the file to write to
 * @param allocator the allocator of byte bufs
 * @param bufferSize the read buffer size (i.e. the size of buffer used for each read operation)
 * @see #readStream(Promise,ByteBufAllocator,int)
 * @see #read(Promise,ByteBufAllocator,int)
 * @return a publisher of the byte bufs
 */
public static Promise<CompositeByteBuf> read(Promise<? extends AsynchronousFileChannel> file,ByteBufAllocator allocator,int bufferSize,long start,long stop){
  return ByteBufStreams.compose(readStream(file,allocator,bufferSize,start,stop),allocator);
}
