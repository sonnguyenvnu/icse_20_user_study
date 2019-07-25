static boolean match(Buffer result,List<byte[]> requestTags,int tagTypeCode){
  ByteBuffer message=result.getBuffer();
  message.mark();
  byte flag=message.get();
  if (!Flags.hasTags(flag)) {
    message.reset();
    return false;
  }
  skip(message,8 + 8);
  skipString(message);
  skipString(message);
  final byte tagsSize=message.get();
  if (tagsSize == 1) {
    final short len=message.getShort();
    final byte[] tag=new byte[len];
    message.get(tag);
    message.reset();
    return matchOneTag(tag,requestTags,tagTypeCode);
  }
  List<byte[]> tags=new ArrayList<>(tagsSize);
  for (int i=0; i < tagsSize; i++) {
    final short len=message.getShort();
    final byte[] bs=new byte[len];
    message.get(bs);
    tags.add(bs);
  }
  message.reset();
  return matchTags(tags,requestTags,tagTypeCode);
}
