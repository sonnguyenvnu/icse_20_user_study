public ReceivedEmail[] receiveEnvelopes(final EmailFilter filter){
  return receiveMessages(filter,null,null,true,null);
}
