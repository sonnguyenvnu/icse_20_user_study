public void write(int b) throws IOException {
  if (this.curr == this.hi) {
    if (this.hitEOF && this.hi < this.maxHi) {
      this.hi++;
    }
 else {
      this.seek(this.curr);
      if (this.curr == this.hi) {
        this.hi++;
      }
    }
  }
  try {
    this.buff[(int)(this.curr - this.lo)]=(byte)b;
    this.curr++;
    this.dirty=true;
  }
 catch (  ArrayIndexOutOfBoundsException e) {
    throw new IOException("Wrote past end of file (increase with setLength)?",e);
  }
}
