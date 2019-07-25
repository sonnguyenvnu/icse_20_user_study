@Override public void truncate(final long byteOffset) throws IOException {
  throwClosed();
  try (RandomAccessFile raf=new RandomAccessFile(file,"rw")){
    raf.setLength(byteOffset);
  }
 }
