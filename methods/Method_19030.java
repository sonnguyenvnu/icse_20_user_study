@UiThread void requestSmoothFocus(int index,int offset,SmoothScrollAlignmentType type){
  if (shouldDispatchRequests()) {
    mTarget.requestSmoothFocus(index,offset,type);
    return;
  }
  queueRequest(index,0);
}
