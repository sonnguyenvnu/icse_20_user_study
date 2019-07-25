public final Object[] write(Object[] outBuf) throws IOException, InterruptedException {
synchronized (this) {
    while (!this.isIdle)     this.wait();
  }
  Object[] res=this.buf;
  this.buf=outBuf;
  this.poolFile=new File(this.filePrefix + Integer.toString(this.hiPool++));
  this.isIdle=false;
  this.writer.notify();
  return res;
}
