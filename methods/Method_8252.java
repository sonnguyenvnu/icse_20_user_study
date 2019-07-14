private void inlineUpdate2(){
  if (prevSetUnreadCount != newUnreadMessageCount) {
    prevSetUnreadCount=newUnreadMessageCount;
    pagedownButtonCounter.setText(String.format("%d",newUnreadMessageCount));
  }
  if (pagedownButtonCounter.getVisibility() != View.INVISIBLE) {
    pagedownButtonCounter.setVisibility(View.INVISIBLE);
  }
}
