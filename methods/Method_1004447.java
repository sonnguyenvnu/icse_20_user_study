@SuppressWarnings("unchecked") private MetaInfoRequest deserialize(ByteBuf buf){
  return new MetaInfoRequest(PayloadHolderUtils.readStringHashMap(buf));
}
