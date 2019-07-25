public ByteBuffer execute(ProtocolVersion protocolVersion,List<ByteBuffer> parameters) throws InvalidRequestException {
  assert parameters.size() >= 1 : "Expected at least 1 argument for toJsonArray(), but got " + parameters.size();
  StringBuilder sb=new StringBuilder();
  sb.append('[');
  int i=0;
  for (  ByteBuffer bb : parameters) {
    if (sb.length() > 1)     sb.append(',');
    sb.append(argTypes.get(i).toJSONString(bb,protocolVersion));
    i++;
  }
  sb.append(']');
  return ByteBufferUtil.bytes(sb.toString());
}
