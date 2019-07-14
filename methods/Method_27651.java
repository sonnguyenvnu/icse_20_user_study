@Override public void onSendActionClicked(@NonNull String text,Bundle bundle){
  GistCommentsFragment view=getGistCommentsFragment();
  if (view != null) {
    view.onHandleComment(text,bundle);
  }
}
