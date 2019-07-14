public long getPollId(){
  if (type != TYPE_POLL) {
    return 0;
  }
  return ((TLRPC.TL_messageMediaPoll)messageOwner.media).poll.id;
}
