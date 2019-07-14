public boolean isVoted(){
  if (type != TYPE_POLL) {
    return false;
  }
  TLRPC.TL_messageMediaPoll mediaPoll=(TLRPC.TL_messageMediaPoll)messageOwner.media;
  if (mediaPoll.results == null || mediaPoll.results.results.isEmpty()) {
    return false;
  }
  for (int a=0, N=mediaPoll.results.results.size(); a < N; a++) {
    TLRPC.TL_pollAnswerVoters answer=mediaPoll.results.results.get(a);
    if (answer.chosen) {
      return true;
    }
  }
  return false;
}
