@Override public FileChannel position(long pos) throws IOException {
  checkFileSizeLimit(pos);
  this.pos=(int)pos;
  return this;
}
