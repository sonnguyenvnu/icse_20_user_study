private void setupChanges(){
  PullRequest pullRequest=issueCallback.getData();
  if (pullRequest != null) {
    addition.setText(String.valueOf(pullRequest.getAdditions()));
    deletion.setText(String.valueOf(pullRequest.getDeletions()));
    changes.setText(String.valueOf(pullRequest.getChangedFiles()));
  }
}
