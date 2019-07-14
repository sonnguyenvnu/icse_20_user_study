protected boolean isFinished(){
  return currentState == STATE_ENDED || currentState == STATE_FAILED;
}
