private static long find(RandomAccessFile file,byte[] find,boolean quiet) throws IOException {
  long pos=file.getFilePointer();
  long length=file.length();
  int bufferSize=4 * 1024;
  byte[] data=new byte[bufferSize * 2];
  long last=System.nanoTime();
  while (pos < length) {
    System.arraycopy(data,bufferSize,data,0,bufferSize);
    if (pos + bufferSize > length) {
      file.readFully(data,bufferSize,(int)(length - pos));
      return find(data,find,(int)(bufferSize + length - pos - find.length));
    }
    if (!quiet) {
      long now=System.nanoTime();
      if (now > last + TimeUnit.SECONDS.toNanos(5)) {
        System.out.println((100 * pos / length) + "%");
        last=now;
      }
    }
    file.readFully(data,bufferSize,bufferSize);
    int f=find(data,find,bufferSize);
    if (f >= 0) {
      return f + pos - bufferSize;
    }
    pos+=bufferSize;
  }
  return -1;
}
