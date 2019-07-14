@Override public void addComment(@Nullable TimelineModel timelineModel,int index){
  if (timelineModel != null) {
    adapter.addItem(timelineModel,1);
    recycler.smoothScrollToPosition(1);
  }
 else   if (index != -1) {
    recycler.smoothScrollToPosition(index + 1);
    if ((index + 1) > adapter.getItemCount()) {
      showMessage(R.string.error,R.string.comment_is_too_far_to_paginate);
    }
  }
}
