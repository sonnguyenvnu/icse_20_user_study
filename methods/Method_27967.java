@Override public void onSubmit(String username,String name,CommentRequestModel commentRequestModel){
  makeRestCall(RestProvider.getRepoService(isEnterprise()).postCommitComment(username,name,sha,commentRequestModel),newComment -> sendToView(view -> view.onCommentAdded(newComment)));
}
