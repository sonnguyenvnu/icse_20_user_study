void _XXXXX_(final long ledgerId,final byte[] masterKey,final long lac,ByteBufList toSend,WriteLacCallback cb,Object ctx){
  final long txnId=getTxnId();
  final CompletionKey completionKey=new V3CompletionKey(txnId,OperationType.WRITE_LAC);
  completionObjects.put(completionKey,new WriteLacCompletion(completionKey,cb,ctx,lac));
  BKPacketHeader.Builder headerBuilder=BKPacketHeader.newBuilder().setVersion(ProtocolVersion.VERSION_THREE).setOperation(OperationType.WRITE_LAC).setTxnId(txnId);
  ByteString body;
  if (toSend.hasArray()) {
    body=UnsafeByteOperations.unsafeWrap(toSend.array(),toSend.arrayOffset(),toSend.readableBytes());
  }
 else   if (toSend.size() == 1) {
    body=UnsafeByteOperations.unsafeWrap(toSend.getBuffer(0).nioBuffer());
  }
 else {
    body=UnsafeByteOperations.unsafeWrap(toSend.toArray());
  }
  WriteLacRequest.Builder writeLacBuilder=WriteLacRequest.newBuilder().setLedgerId(ledgerId).setLac(lac).setMasterKey(UnsafeByteOperations.unsafeWrap(masterKey)).setBody(body);
  final Request writeLacRequest=withRequestContext(Request.newBuilder()).setHeader(headerBuilder).setWriteLacRequest(writeLacBuilder).build();
  writeAndFlush(channel,completionKey,writeLacRequest);
}