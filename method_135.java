private void _XXXXX_(final OutputStream os,final byte[] bytes) throws IOException {
  int index=0;
  while (index < bytes.length) {
    final int blockSize=Math.min(bytes.length - index,255);
    os.write(blockSize);
    os.write(bytes,index,blockSize);
    index+=blockSize;
  }
  os.write(0);
}