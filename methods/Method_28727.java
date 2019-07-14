public Long getIntegerReply(){
  flush();
  return (Long)readProtocolWithCheckingBroken();
}
