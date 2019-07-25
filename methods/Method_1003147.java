/** 
 * Roll back to a given revision into a file called *.temp.
 * @param fileName the file name
 * @param targetVersion the version to roll back to (Long.MAX_VALUE for thelatest version)
 * @param writer the log writer
 * @return the version rolled back to (-1 if no version)
 */
public static long rollback(String fileName,long targetVersion,Writer writer){
  long newestVersion=-1;
  PrintWriter pw=new PrintWriter(writer,true);
  if (!FilePath.get(fileName).exists()) {
    pw.println("File not found: " + fileName);
    return newestVersion;
  }
  FileChannel file=null;
  FileChannel target=null;
  int blockSize=MVStore.BLOCK_SIZE;
  try {
    file=FilePath.get(fileName).open("r");
    FilePath.get(fileName + ".temp").delete();
    target=FilePath.get(fileName + ".temp").open("rw");
    long fileSize=file.size();
    ByteBuffer block=ByteBuffer.allocate(4096);
    Chunk newestChunk=null;
    for (long pos=0; pos < fileSize; ) {
      block.rewind();
      DataUtils.readFully(file,pos,block);
      block.rewind();
      int headerType=block.get();
      if (headerType == 'H') {
        block.rewind();
        target.write(block,pos);
        pos+=blockSize;
        continue;
      }
      if (headerType != 'c') {
        pos+=blockSize;
        continue;
      }
      Chunk c=null;
      try {
        c=Chunk.readChunkHeader(block,pos);
      }
 catch (      IllegalStateException e) {
        pos+=blockSize;
        continue;
      }
      if (c.len <= 0) {
        pos+=blockSize;
        continue;
      }
      int length=c.len * MVStore.BLOCK_SIZE;
      ByteBuffer chunk=ByteBuffer.allocate(length);
      DataUtils.readFully(file,pos,chunk);
      if (c.version > targetVersion) {
        pos+=length;
        continue;
      }
      chunk.rewind();
      target.write(chunk,pos);
      if (newestChunk == null || c.version > newestChunk.version) {
        newestChunk=c;
        newestVersion=c.version;
      }
      pos+=length;
    }
    int length=newestChunk.len * MVStore.BLOCK_SIZE;
    ByteBuffer chunk=ByteBuffer.allocate(length);
    DataUtils.readFully(file,newestChunk.block * MVStore.BLOCK_SIZE,chunk);
    chunk.rewind();
    target.write(chunk,fileSize);
  }
 catch (  IOException e) {
    pw.println("ERROR: " + e);
    e.printStackTrace(pw);
  }
 finally {
    if (file != null) {
      try {
        file.close();
      }
 catch (      IOException e) {
      }
    }
    if (target != null) {
      try {
        target.close();
      }
 catch (      IOException e) {
      }
    }
  }
  pw.flush();
  return newestVersion;
}
