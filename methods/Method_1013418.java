public final Object[] read(Object[] inBuf) throws IOException, InterruptedException {
  if (this.hiPool == 0)   return null;
synchronized (this) {
    while (!this.isIdle)     this.wait();
  }
  Object[] res=this.buf;
  this.buf=inBuf;
  this.hiPool--;
  if (this.hiPool > 0) {
    this.poolFile=new File(this.filePrefix + Integer.toString(this.hiPool - 1));
    this.isIdle=false;
    this.reader.notify();
  }
  return res;
}
