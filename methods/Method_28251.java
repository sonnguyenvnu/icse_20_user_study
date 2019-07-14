@Override public void onSendActionClicked(@NonNull String text,Bundle bundle){
  PullRequestTimelineFragment fragment=getPullRequestTimelineFragment();
  if (fragment != null) {
    fragment.onHandleComment(text,bundle);
  }
}
