@Override public void onSubmit(@NonNull String login,@NonNull String repoId,int issueNumber,@NonNull String text){
  CommentRequestModel requestModel=new CommentRequestModel();
  requestModel.setBody(text);
  makeRestCall(RestProvider.getIssueService(isEnterprise()).createIssueComment(login,repoId,issueNumber,requestModel),comment -> sendToView(IssuePopupMvp.View::onSuccessfullySubmitted));
}
