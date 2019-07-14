/** 
 * Write file.
 * @param fullFileName the full file name
 * @param contents     the contents
 */
public static void writeFile(final String fullFileName,final byte[] contents){
  try {
    RandomAccessFile raf=new RandomAccessFile(fullFileName,"rw");
    try (FileChannel channel=raf.getChannel()){
      ByteBuffer buffer=ByteBuffer.allocate(contents.length);
      buffer.put(contents);
      buffer.flip();
      while (buffer.hasRemaining()) {
        channel.write(buffer);
      }
      channel.force(true);
    }
   }
 catch (  IOException e) {
    e.printStackTrace();
  }
}
