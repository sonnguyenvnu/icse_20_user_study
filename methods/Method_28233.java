@Override public void onCommentAdded(@NonNull String comment,@NonNull CommitLinesModel item,Bundle bundle){
  if (bundle != null) {
    String path=bundle.getString(BundleConstant.ITEM);
    if (path == null)     return;
    CommentRequestModel commentRequestModel=new CommentRequestModel();
    commentRequestModel.setBody(comment);
    commentRequestModel.setPath(path);
    commentRequestModel.setPosition(item.getPosition());
    if (viewCallback != null)     viewCallback.onAddComment(commentRequestModel);
    int groupPosition=bundle.getInt(BundleConstant.EXTRA_TWO);
    int childPosition=bundle.getInt(BundleConstant.EXTRA_THREE);
    CommitFileChanges commitFileChanges=adapter.getItem(groupPosition);
    List<CommitLinesModel> models=commitFileChanges.getLinesModel();
    if (models != null && !models.isEmpty()) {
      CommitLinesModel current=models.get(childPosition);
      if (current != null) {
        current.setHasCommentedOn(true);
      }
      models.set(childPosition,current);
      adapter.notifyItemChanged(groupPosition);
    }
  }
}
