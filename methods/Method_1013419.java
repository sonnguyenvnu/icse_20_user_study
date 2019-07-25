public final void recover(ObjectInputStream ois) throws IOException {
  this.hiPool=ois.readInt();
  if (this.hiPool > 0) {
    this.poolFile=new File(this.filePrefix + Integer.toString(this.hiPool - 1));
    this.isIdle=false;
    this.reader.notify();
  }
}
