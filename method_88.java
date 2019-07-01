private static ByteBuf _XXXXX_(MessageLite msg,ByteBufAllocator allocator){
  int size=msg.getSerializedSize();
  ByteBuf buf=allocator.heapBuffer(size,size);
  try {
    msg.writeTo(CodedOutputStream.newInstance(buf.array(),buf.arrayOffset() + buf.writerIndex(),size));
  }
 catch (  IOException e) {
    throw new RuntimeException(e);
  }
  buf.writerIndex(buf.capacity());
  return buf;
}