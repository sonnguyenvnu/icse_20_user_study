@Override public void onSetup(){
  hideProgress();
  if (getPresenter().getCommit() == null) {
    return;
  }
  supportInvalidateOptionsMenu();
  Commit commit=getPresenter().getCommit();
  String login=commit.getAuthor() != null ? commit.getAuthor().getLogin() : commit.getGitCommit().getAuthor().getName();
  String avatar=commit.getAuthor() != null ? commit.getAuthor().getAvatarUrl() : null;
  Date dateValue=commit.getGitCommit().getAuthor().getDate();
  HtmlHelper.htmlIntoTextView(title,commit.getGitCommit().getMessage(),title.getWidth());
  setTaskName(commit.getLogin() + "/" + commit.getRepoId() + " - Commit " + StringsKt.take(commit.getSha(),5));
  detailsIcon.setVisibility(View.VISIBLE);
  size.setVisibility(View.GONE);
  date.setText(SpannableBuilder.builder().bold(getPresenter().repoId).append(" ").append(" ").append(ParseDateFormat.getTimeAgo(dateValue)));
  avatarLayout.setUrl(avatar,login,false,LinkParserHelper.isEnterprise(commit.getHtmlUrl()));
  addition.setText(String.valueOf(commit.getStats() != null ? commit.getStats().getAdditions() : 0));
  deletion.setText(String.valueOf(commit.getStats() != null ? commit.getStats().getDeletions() : 0));
  changes.setText(String.valueOf(commit.getFiles() != null ? commit.getFiles().size() : 0));
  pager.setAdapter(new FragmentsPagerAdapter(getSupportFragmentManager(),FragmentPagerAdapterModel.buildForCommit(this,commit)));
  tabs.setupWithViewPager(pager);
  pager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
    @Override public void onPageSelected(    int position){
      super.onPageSelected(position);
      hideShowFab();
    }
  }
);
  hideShowFab();
  TabLayout.Tab tabOne=tabs.getTabAt(0);
  TabLayout.Tab tabTwo=tabs.getTabAt(1);
  if (tabOne != null && commit.getFiles() != null) {
    tabOne.setText(getString(R.string.files) + " (" + commit.getFiles().size() + ")");
  }
  if (tabTwo != null && commit.getGitCommit() != null && commit.getGitCommit().getCommentCount() > 0) {
    tabTwo.setText(getString(R.string.comments) + " (" + commit.getGitCommit().getCommentCount() + ")");
  }
  tabs.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(pager){
    @Override public void onTabReselected(    TabLayout.Tab tab){
      super.onTabReselected(tab);
      onScrollTop(tab.getPosition());
    }
  }
);
}
