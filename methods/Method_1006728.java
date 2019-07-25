/** 
 * Open the data of the zip entry as an  {@link InputStream}, inflating the data if the entry is deflated.
 * @return the input stream
 * @throws IOException If an I/O exception occurs.
 * @throws InterruptedException if the thread was interrupted.
 */
public InputStream open() throws IOException, InterruptedException {
  if (recyclableInflaterInstance != null) {
    throw new IOException("Zip entry already open");
  }
  if (isDeflated) {
    recyclableInflaterInstance=nestedJarHandler.inflaterRecycler.acquire();
  }
  return new InputStream(){
{
      currChunkIdx=(int)(dataStartOffsetWithinPhysicalZipFile / FileUtils.MAX_BUFFER_SIZE);
      currChunkByteBuf=parentLogicalZipFile.physicalZipFile.getByteBuffer(currChunkIdx).duplicate();
      final int chunkPos=(int)(dataStartOffsetWithinPhysicalZipFile - (((long)currChunkIdx) * (long)FileUtils.MAX_BUFFER_SIZE));
      ((Buffer)currChunkByteBuf).position(chunkPos);
      final long endPos=chunkPos + compressedSize;
      ((Buffer)currChunkByteBuf).limit((int)Math.min(FileUtils.MAX_BUFFER_SIZE,endPos));
      isLastChunk=endPos <= FileUtils.MAX_BUFFER_SIZE;
    }
    /** 
 * Advance to the next 2GB chunk. 
 */
    private boolean readNextChunk() throws IOException, InterruptedException {
      currChunkIdx++;
      if (currChunkIdx >= parentLogicalZipFile.physicalZipFile.numMappedByteBuffers) {
        return false;
      }
      final long chunkStartOff=((long)currChunkIdx) * (long)FileUtils.MAX_BUFFER_SIZE;
      final long priorBytes=chunkStartOff - dataStartOffsetWithinPhysicalZipFile;
      final long remainingBytes=compressedSize - priorBytes;
      if (remainingBytes <= 0) {
        return false;
      }
      currChunkByteBuf=parentLogicalZipFile.physicalZipFile.getByteBuffer(currChunkIdx).duplicate();
      ((Buffer)currChunkByteBuf).position(0);
      ((Buffer)currChunkByteBuf).limit((int)Math.min(FileUtils.MAX_BUFFER_SIZE,remainingBytes));
      isLastChunk=remainingBytes <= FileUtils.MAX_BUFFER_SIZE;
      return true;
    }
    /** 
 * Inflate deflated data.
 * @param buf the buffer to inflate into.
 * @param off the offset within buf to start writing.
 * @param len the number of bytes of uncompressed data to read.
 * @return the number of bytes read.
 * @throws IOException if an I/O exception occurred.
 * @throws InterruptedException if the thread was interrupted.
 */
    private int readDeflated(    final byte[] buf,    final int off,    final int len) throws IOException, InterruptedException {
      try {
        final byte[] inflateBuf=new byte[INFLATE_BUF_SIZE];
        int numInflatedBytes;
        while ((numInflatedBytes=inflater.inflate(buf,off,len)) == 0) {
          if (inflater.finished() || inflater.needsDictionary()) {
            eof=true;
            return -1;
          }
          if (inflater.needsInput()) {
            if (!currChunkByteBuf.hasRemaining() && !(readNextChunk() && currChunkByteBuf.hasRemaining())) {
              throw new IOException("Unexpected EOF in deflated data");
            }
            try {
              final int remaining=currChunkByteBuf.remaining();
              if (isLastChunk && remaining < inflateBuf.length) {
                currChunkByteBuf.get(inflateBuf,0,remaining);
                inflateBuf[remaining]=(byte)0;
                inflater.setInput(inflateBuf,0,remaining + 1);
              }
 else               if (isLastChunk && remaining == inflateBuf.length) {
                currChunkByteBuf.get(inflateBuf,0,remaining - 1);
                inflater.setInput(inflateBuf,0,remaining - 1);
              }
 else {
                final int bytesToRead=Math.min(inflateBuf.length,remaining);
                currChunkByteBuf.get(inflateBuf,0,bytesToRead);
                inflater.setInput(inflateBuf,0,bytesToRead);
              }
            }
 catch (            final BufferUnderflowException e) {
              throw new IOException("Unexpected EOF in deflated data");
            }
          }
        }
        return numInflatedBytes;
      }
 catch (      final DataFormatException e) {
        throw new ZipException(e.getMessage() != null ? e.getMessage() : "Invalid deflated zip entry data");
      }
    }
    /** 
 * Copy stored (non-deflated) data from ByteBuffer to target buffer.
 * @param buf the buffer to copy the stored entry into.
 * @param off the offset within buf to start writing.
 * @param len the number of bytes to read.
 * @return the number of bytes read.
 * @throws IOException if an I/O exception occurred.
 * @throws InterruptedException if the thread was interrupted.
 */
    private int readStored(    final byte[] buf,    final int off,    final int len) throws IOException, InterruptedException {
      int read=0;
      while (read < len) {
        if (!currChunkByteBuf.hasRemaining() && !readNextChunk()) {
          return read == 0 ? -1 : read;
        }
        final int remainingToRead=len - read;
        final int remainingInBuf=currChunkByteBuf.remaining();
        final int numBytesRead=Math.min(remainingToRead,remainingInBuf);
        try {
          currChunkByteBuf.get(buf,off + read,numBytesRead);
        }
 catch (        final BufferUnderflowException e) {
          throw new EOFException("Unexpected EOF in stored (non-deflated) zip entry data");
        }
        read+=numBytesRead;
      }
      return read;
    }
    @Override public int read(    final byte[] buf,    final int off,    final int len) throws IOException {
      if (closed.get()) {
        throw new IOException("Stream closed");
      }
      if (buf == null) {
        throw new NullPointerException();
      }
 else       if (off < 0 || len < 0 || len > buf.length - off) {
        throw new IndexOutOfBoundsException();
      }
 else       if (len == 0) {
        return 0;
      }
 else       if (parentLogicalZipFile.physicalZipFile.fileLen == 0) {
        return -1;
      }
      try {
        if (isDeflated) {
          return readDeflated(buf,off,len);
        }
 else {
          return readStored(buf,off,len);
        }
      }
 catch (      final InterruptedException e) {
        nestedJarHandler.interruptionChecker.interrupt();
        throw new IOException("Thread was interrupted");
      }
    }
    @Override public int read() throws IOException {
      if (closed.get()) {
        throw new IOException("Stream closed");
      }
      return read(scratch,0,1) == -1 ? -1 : scratch[0] & 0xff;
    }
    @Override public int available() throws IOException {
      if (closed.get()) {
        throw new IOException("Stream closed");
      }
      if (inflater.finished()) {
        eof=true;
      }
      return eof ? 0 : 1;
    }
    @Override public long skip(    final long n) throws IOException {
      if (closed.get()) {
        throw new IOException("Stream closed");
      }
      if (n < 0) {
        throw new IllegalArgumentException("Invalid skip value");
      }
      long total=0;
      while (total < n) {
        final int numSkipped=read(scratch,0,(int)Math.min(n - total,scratch.length));
        if (numSkipped == -1) {
          eof=true;
          break;
        }
        total+=numSkipped;
      }
      return total;
    }
    @Override public boolean markSupported(){
      return false;
    }
    @Override public synchronized void mark(    final int readlimit){
      throw new IllegalArgumentException("Not supported");
    }
    @Override public synchronized void reset() throws IOException {
      throw new IllegalArgumentException("Not supported");
    }
    @Override public void close() throws IOException {
      if (!closed.getAndSet(true)) {
        currChunkByteBuf=null;
        if (recyclableInflaterInstance != null) {
          nestedJarHandler.inflaterRecycler.recycle(recyclableInflaterInstance);
          recyclableInflaterInstance=null;
        }
      }
    }
  }
;
}
