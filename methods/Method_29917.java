@Override public void onCommentListAppended(int requestCode,List<Comment> appendedCommentList){
  mCommentAdapter.addAll(appendedCommentList);
  BroadcastCommentCountFixer.onCommentListChanged(mResource.getEffectiveBroadcast(),mResource.getCommentList(),this);
}
