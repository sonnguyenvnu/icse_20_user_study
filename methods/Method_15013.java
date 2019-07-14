@Override public void bindView(List<CommentItem> list){
  this.data=list;
  int count=list == null ? 0 : list.size();
  boolean showMore=maxShowCount > 0 && count > maxShowCount;
  tvCommentContainerViewMore.setVisibility(showMore ? View.VISIBLE : View.GONE);
  llCommentContainerViewContainer.setVisibility(count <= 0 ? View.GONE : View.VISIBLE);
  llCommentContainerViewContainer.removeAllViews();
  if (count > 0) {
    if (showMore) {
      list=list.subList(0,maxShowCount);
    }
    for (int i=0; i < list.size(); i++) {
      addCommentView(i,list.get(i));
    }
  }
}
