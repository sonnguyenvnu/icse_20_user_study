public static void updatePollResults(TLRPC.TL_messageMediaPoll media,TLRPC.TL_pollResults results){
  if ((results.flags & 2) != 0) {
    byte[] chosen=null;
    if (results.min && media.results.results != null) {
      for (int b=0, N2=media.results.results.size(); b < N2; b++) {
        TLRPC.TL_pollAnswerVoters answerVoters=media.results.results.get(b);
        if (answerVoters.chosen) {
          chosen=answerVoters.option;
          break;
        }
      }
    }
    media.results.results=results.results;
    if (chosen != null) {
      for (int b=0, N2=media.results.results.size(); b < N2; b++) {
        TLRPC.TL_pollAnswerVoters answerVoters=media.results.results.get(b);
        if (Arrays.equals(answerVoters.option,chosen)) {
          answerVoters.chosen=true;
          break;
        }
      }
    }
    media.results.flags|=2;
  }
  if ((results.flags & 4) != 0) {
    media.results.total_voters=results.total_voters;
    media.results.flags|=4;
  }
}
