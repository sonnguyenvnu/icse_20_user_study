@Override public void onSendActionClicked(@NonNull String text,@Nullable Bundle bundle){
  IssueTimelineFragment fragment=getIssueTimelineFragment();
  if (fragment != null) {
    fragment.onHandleComment(text,bundle);
  }
}
