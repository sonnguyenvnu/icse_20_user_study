private void initTabs(@NonNull PullRequest pullRequest){
  TabLayout.Tab tab1=tabs.getTabAt(0);
  TabLayout.Tab tab2=tabs.getTabAt(1);
  TabLayout.Tab tab3=tabs.getTabAt(2);
  if (tab3 != null) {
    tab3.setText(SpannableBuilder.builder().append(getString(R.string.files)).append(" ").append("(").append(String.valueOf(pullRequest.getChangedFiles())).append(")"));
  }
  if (tab2 != null) {
    tab2.setText(SpannableBuilder.builder().append(getString(R.string.commits)).append(" ").append("(").append(String.valueOf(pullRequest.getCommits())).append(")"));
  }
  if (tab1 != null) {
    tab1.setText(SpannableBuilder.builder().append(getString(R.string.details)).append(" ").append("(").append(String.valueOf(pullRequest.getComments())).append(")"));
  }
}
