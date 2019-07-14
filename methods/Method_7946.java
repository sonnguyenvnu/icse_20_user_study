private boolean checkPollButtonMotionEvent(MotionEvent event){
  if (currentMessageObject.eventId != 0 || pollVoted || pollClosed || pollVoteInProgress || pollUnvoteInProgress || pollButtons.isEmpty() || currentMessageObject.type != 17 || !currentMessageObject.isSent()) {
    return false;
  }
  int x=(int)event.getX();
  int y=(int)event.getY();
  boolean result=false;
  if (event.getAction() == MotionEvent.ACTION_DOWN) {
    pressedVoteButton=-1;
    for (int a=0; a < pollButtons.size(); a++) {
      PollButton button=pollButtons.get(a);
      int y2=button.y + namesOffset - AndroidUtilities.dp(13);
      if (x >= button.x && x <= button.x + backgroundWidth - AndroidUtilities.dp(31) && y >= y2 && y <= y2 + button.height + AndroidUtilities.dp(26)) {
        pressedVoteButton=a;
        if (Build.VERSION.SDK_INT >= 21 && selectorDrawable != null) {
          selectorDrawable.setBounds(button.x - AndroidUtilities.dp(9),y2,button.x + backgroundWidth - AndroidUtilities.dp(22),y2 + button.height + AndroidUtilities.dp(26));
          selectorDrawable.setState(pressedState);
          selectorDrawable.setHotspot(x,y);
        }
        invalidate();
        result=true;
        break;
      }
    }
  }
 else {
    if (event.getAction() == MotionEvent.ACTION_UP) {
      if (pressedVoteButton != -1) {
        playSoundEffect(SoundEffectConstants.CLICK);
        if (Build.VERSION.SDK_INT >= 21 && selectorDrawable != null) {
          selectorDrawable.setState(StateSet.NOTHING);
        }
        pollVoteInProgressNum=pressedVoteButton;
        pollVoteInProgress=true;
        voteCurrentProgressTime=0.0f;
        firstCircleLength=true;
        voteCurrentCircleLength=360;
        voteRisingCircleLength=false;
        delegate.didPressVoteButton(this,pollButtons.get(pressedVoteButton).answer);
        pressedVoteButton=-1;
        invalidate();
      }
    }
 else     if (event.getAction() == MotionEvent.ACTION_MOVE) {
      if (pressedVoteButton != -1 && Build.VERSION.SDK_INT >= 21 && selectorDrawable != null) {
        selectorDrawable.setHotspot(x,y);
      }
    }
  }
  return result;
}
