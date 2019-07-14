@SuppressWarnings("unchecked") public List<byte[]> getBinaryMultiBulkReply(){
  flush();
  return (List<byte[]>)readProtocolWithCheckingBroken();
}
