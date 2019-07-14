@Override public void onSendActionClicked(@NonNull String text,Bundle bundle){
  CommitCommentsFragment fragment=getCommitCommentsFragment();
  if (fragment != null) {
    fragment.onHandleComment(text,bundle);
  }
}
