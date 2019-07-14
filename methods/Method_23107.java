public void writeUShort(int v) throws IOException {
  out.write((v >> 8) & 0xff);
  out.write((v >>> 0) & 0xff);
  incCount(2);
}
