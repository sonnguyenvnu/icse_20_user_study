private void inlineUpdate1(){
  if (prevSetUnreadCount != newUnreadMessageCount) {
    prevSetUnreadCount=newUnreadMessageCount;
    pagedownButtonCounter.setText(String.format("%d",newUnreadMessageCount));
  }
  if (newUnreadMessageCount <= 0) {
    if (pagedownButtonCounter.getVisibility() != View.INVISIBLE) {
      pagedownButtonCounter.setVisibility(View.INVISIBLE);
    }
  }
 else {
    if (pagedownButtonCounter.getVisibility() != View.VISIBLE) {
      pagedownButtonCounter.setVisibility(View.VISIBLE);
    }
  }
}
