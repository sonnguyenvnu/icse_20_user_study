public void close(){
  if (sbuf.length <= 1024 * 8) {
    SBUF_LOCAL.set(sbuf);
  }
  this.sbuf=null;
}
