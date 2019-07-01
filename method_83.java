private void _XXXXX_(final OutputStream os,final Palette palette) throws IOException {
  final int length=palette.length();
  final byte[] bytes=new byte[length * 3];
  for (int i=0; i < length; i++) {
    final int rgb=palette.getEntry(i);
    final int index=i * 3;
    bytes[index + 0]=(byte)(0xff & (rgb >> 16));
    bytes[index + 1]=(byte)(0xff & (rgb >> 8));
    bytes[index + 2]=(byte)(0xff & (rgb >> 0));
  }
  writeChunk(os,ChunkType.PLTE,bytes);
}