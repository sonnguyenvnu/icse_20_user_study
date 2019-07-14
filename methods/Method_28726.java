public byte[] getBinaryBulkReply(){
  flush();
  return (byte[])readProtocolWithCheckingBroken();
}
