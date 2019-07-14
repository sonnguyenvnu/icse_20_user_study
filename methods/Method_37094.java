public void updateCurrCard(){
  if (recyclerView == null || lastMotionEvent == null) {
    return;
  }
  View childView=recyclerView.findChildViewUnder(lastMotionEvent.getX(),lastMotionEvent.getY());
  if (childView != null) {
    int position=layoutManager.getPosition(childView);
    currCardIdx=mGroupBasicAdapter.findCardIdxFor(position);
    List<Card> groups=mGroupBasicAdapter.getGroups();
    if (currCardIdx >= groups.size() || currCardIdx < 0) {
      Log.e(TAG,"onScroll: group size >= cardIdx");
      return;
    }
    currCard=groups.get(currCardIdx);
  }
}
