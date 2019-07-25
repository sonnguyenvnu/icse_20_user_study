public ByteBuffer execute(ProtocolVersion protocolVersion,List<ByteBuffer> parameters) throws InvalidRequestException {
  ByteBuffer bb=parameters.get(0);
  if (bb == null)   return ByteBufferUtil.bytes("null");
  return ByteBufferUtil.bytes(argTypes.get(0).getSerializer().deserialize(bb).toString());
}
