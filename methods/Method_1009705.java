public void reset(Context mContext,SubmissionComments dataSet,RecyclerView listView,Submission submission,boolean reset){
  doTimes();
  this.mContext=mContext;
  this.listView=listView;
  this.dataSet=dataSet;
  this.submission=submission;
  hidden=new HashSet<>();
  currentComments=dataSet.comments;
  if (currentComments != null) {
    for (int i=0; i < currentComments.size(); i++) {
      keys.put(currentComments.get(i).getName(),i);
    }
  }
  hiddenPersons=new ArrayList<>();
  toCollapse=new ArrayList<>();
  if (currentSelectedItem != null && !currentSelectedItem.isEmpty() && !reset) {
    notifyDataSetChanged();
  }
 else {
    if (currentComments != null && !reset) {
      notifyItemRangeChanged(2,currentComments.size() + 1);
    }
 else     if (currentComments == null) {
      currentComments=new ArrayList<>();
      notifyDataSetChanged();
    }
 else {
      notifyDataSetChanged();
    }
  }
  if (currentSelectedItem != null && !currentSelectedItem.isEmpty() && currentComments != null && !currentComments.isEmpty()) {
    int i=2;
    for (    CommentObject n : currentComments) {
      if (n instanceof CommentItem && n.comment.getComment().getFullName().contains(currentSelectedItem)) {
        ((PreCachingLayoutManagerComments)listView.getLayoutManager()).scrollToPositionWithOffset(i,mPage.headerHeight);
        break;
      }
      i++;
    }
    mPage.resetScroll(true);
  }
  if (mContext instanceof BaseActivity) {
    ((BaseActivity)mContext).setShareUrl("https://reddit.com" + submission.getPermalink());
  }
}
