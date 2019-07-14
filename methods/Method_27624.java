@Override public void onHandleDeletion(@Nullable Bundle bundle){
  if (bundle != null) {
    long commId=bundle.getLong(BundleConstant.EXTRA,0);
    String gistId=bundle.getString(BundleConstant.ID);
    if (commId != 0 && gistId != null) {
      makeRestCall(RestProvider.getGistService(isEnterprise()).deleteGistComment(gistId,commId),booleanResponse -> sendToView(view -> {
        if (booleanResponse.code() == 204) {
          Comment comment=new Comment();
          comment.setId(commId);
          view.onRemove(comment);
        }
 else {
          view.showMessage(R.string.error,R.string.error_deleting_comment);
        }
      }
));
    }
  }
}
