@Override public void onSetupDetails(){
  hideProgress();
  Gist gistsModel=getPresenter().getGist();
  if (gistsModel == null) {
    return;
  }
  onUpdatePinIcon(gistsModel);
  String url=gistsModel.getOwner() != null ? gistsModel.getOwner().getAvatarUrl() : gistsModel.getUser() != null ? gistsModel.getUser().getAvatarUrl() : "";
  String login=gistsModel.getOwner() != null ? gistsModel.getOwner().getLogin() : gistsModel.getUser() != null ? gistsModel.getUser().getLogin() : "";
  avatarLayout.setUrl(url,login,false,LinkParserHelper.isEnterprise(gistsModel.getHtmlUrl()));
  title.setText(gistsModel.getDisplayTitle(false,true));
  setTaskName(gistsModel.getDisplayTitle(false,true).toString());
  edit.setVisibility(Login.getUser().getLogin().equals(login) ? View.VISIBLE : View.GONE);
  detailsIcon.setVisibility(InputHelper.isEmpty(gistsModel.getDescription()) || !ViewHelper.isEllipsed(title) ? View.GONE : View.VISIBLE);
  if (gistsModel.getCreatedAt().before(gistsModel.getUpdatedAt())) {
    date.setText(String.format("%s %s",ParseDateFormat.getTimeAgo(gistsModel.getCreatedAt()),getString(R.string.edited)));
  }
 else {
    date.setText(ParseDateFormat.getTimeAgo(gistsModel.getCreatedAt()));
  }
  size.setText(Formatter.formatFileSize(this,gistsModel.getSize()));
  pager.setAdapter(new FragmentsPagerAdapter(getSupportFragmentManager(),FragmentPagerAdapterModel.buildForGist(this,gistsModel)));
  tabs.setupWithViewPager(pager);
  pager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
    @Override public void onPageSelected(    int position){
      super.onPageSelected(position);
      hideShowFab();
    }
  }
);
  supportInvalidateOptionsMenu();
  onGistForked(getPresenter().isForked());
  onGistStarred(getPresenter().isStarred());
  hideShowFab();
  tabs.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(pager){
    @Override public void onTabReselected(    TabLayout.Tab tab){
      super.onTabReselected(tab);
      onScrollTop(tab.getPosition());
    }
  }
);
}
