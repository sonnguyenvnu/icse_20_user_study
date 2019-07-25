@Override public void seek(long pos) throws IOException {
  if (Objects.isNull(randomAccessFile)) {
    this.randomAccessFile=new RandomAccessFile(path.toFile(),"r");
  }
  randomAccessFile.seek(pos);
}
