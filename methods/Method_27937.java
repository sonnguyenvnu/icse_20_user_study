@Override public void onHandleDeletion(@Nullable Bundle bundle){
  if (bundle != null) {
    long commId=bundle.getLong(BundleConstant.EXTRA,0);
    if (commId != 0) {
      makeRestCall(RestProvider.getRepoService(isEnterprise()).deleteComment(login,repoId,commId),booleanResponse -> sendToView(view -> {
        if (booleanResponse.code() == 204) {
          Comment comment=new Comment();
          comment.setId(commId);
          view.onRemove(TimelineModel.constructComment(comment));
        }
 else {
          view.showMessage(R.string.error,R.string.error_deleting_comment);
        }
      }
));
    }
  }
}
