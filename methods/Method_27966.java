@Override public void onSubmitComment(@NonNull String comment,@NonNull CommitLinesModel item,@Nullable Bundle bundle){
  if (bundle != null) {
    String blob=bundle.getString(BundleConstant.ITEM);
    String path=bundle.getString(BundleConstant.EXTRA);
    if (path == null || sha == null)     return;
    CommentRequestModel commentRequestModel=new CommentRequestModel();
    commentRequestModel.setBody(comment);
    commentRequestModel.setPath(path);
    commentRequestModel.setPosition(item.getPosition());
    commentRequestModel.setLine(item.getRightLineNo() > 0 ? item.getRightLineNo() : item.getLeftLineNo());
    NameParser nameParser=new NameParser(blob);
    onSubmit(nameParser.getUsername(),nameParser.getName(),commentRequestModel);
  }
}
