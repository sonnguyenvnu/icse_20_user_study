public void writeInt24(int v) throws IOException {
  byteBuf[0]=(byte)(v >>> 16);
  byteBuf[1]=(byte)(v >>> 8);
  byteBuf[2]=(byte)(v >>> 0);
  write(byteBuf,0,3);
}
