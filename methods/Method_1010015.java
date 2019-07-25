private void send(String comment){
  FragmentActivity activity=mCallback.getActivity();
  CoordinatorLayout rootLayout=mCallback.getRootLayout();
  mCallback.onEditorDoSend(comment).compose(RxUtils.wrapForBackgroundTask(activity,rootLayout,R.string.saving_comment,R.string.issue_error_comment)).subscribe(result -> {
    mCallback.onEditorTextSent();
    setCommentText(null,true);
    setAdvancedMode(false);
  }
,error -> Log.d(Gh4Application.LOG_TAG,"Sending comment failed",error));
}
