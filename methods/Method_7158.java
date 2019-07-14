public byte[] isSendingVote(MessageObject messageObject){
  if (messageObject == null) {
    return null;
  }
  final String key="poll_" + messageObject.getPollId();
  return waitingForVote.get(key);
}
