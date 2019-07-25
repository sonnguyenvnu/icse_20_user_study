public static RemotingCommand decode(final ByteBuffer byteBuffer){
  int length=byteBuffer.limit();
  int oriHeaderLen=byteBuffer.getInt();
  int headerLength=getHeaderLength(oriHeaderLen);
  byte[] headerData=new byte[headerLength];
  byteBuffer.get(headerData);
  RemotingCommand cmd=headerDecode(headerData,getProtocolType(oriHeaderLen));
  int bodyLength=length - 4 - headerLength;
  byte[] bodyData=null;
  if (bodyLength > 0) {
    bodyData=new byte[bodyLength];
    byteBuffer.get(bodyData);
  }
  cmd.body=bodyData;
  return cmd;
}
