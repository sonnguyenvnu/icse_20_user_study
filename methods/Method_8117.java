public void setItem(int a,int index,MessageObject messageObject){
  messageObjects[a]=messageObject;
  indeces[a]=index;
  if (messageObject != null) {
    photoVideoViews[a].setVisibility(VISIBLE);
    photoVideoViews[a].setMessageObject(messageObject);
  }
 else {
    photoVideoViews[a].clearAnimation();
    photoVideoViews[a].setVisibility(INVISIBLE);
    messageObjects[a]=null;
  }
}
