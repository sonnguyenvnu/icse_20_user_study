public void hangUp(Runnable onDone){
  declineIncomingCall(currentState == STATE_RINGING || (currentState == STATE_WAITING && isOutgoing) ? DISCARD_REASON_MISSED : DISCARD_REASON_HANGUP,onDone);
}
