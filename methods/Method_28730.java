@SuppressWarnings("unchecked") public List<Object> getRawObjectMultiBulkReply(){
  return (List<Object>)readProtocolWithCheckingBroken();
}
