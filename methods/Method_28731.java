@SuppressWarnings("unchecked") public List<Long> getIntegerMultiBulkReply(){
  flush();
  return (List<Long>)readProtocolWithCheckingBroken();
}
