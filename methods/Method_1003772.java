public static ByteBuf read(ByteBufAllocator allocator,Path path) throws IOException {
  try (SeekableByteChannel sbc=Files.newByteChannel(path);InputStream in=Channels.newInputStream(sbc)){
    int size=Ints.checkedCast(sbc.size());
    ByteBuf byteBuf=allocator.directBuffer(size,size);
    byteBuf.writeBytes(in,size);
    return byteBuf;
  }
 }
