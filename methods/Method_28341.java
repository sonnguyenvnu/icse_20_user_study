@Override public void onInitRepo(){
  hideProgress();
  if (getPresenter().getRepo() == null) {
    return;
  }
switch (showWhich) {
case 1:
    onLongClick(watchRepoLayout);
  break;
case 2:
onLongClick(starRepoLayout);
break;
case 3:
onLongClick(forkRepoLayout);
break;
case 4:
MilestoneDialogFragment.newInstance(login,repoId).show(getSupportFragmentManager(),"MilestoneDialogFragment");
break;
case 5:
LabelsDialogFragment.newInstance(null,repoId,login).show(getSupportFragmentManager(),"LabelsDialogFragment");
break;
}
showWhich=-1;
setTaskName(getPresenter().getRepo().getFullName());
Repo repoModel=getPresenter().getRepo();
if (repoModel.isHasProjects()) {
bottomNavigation.inflateMenu(R.menu.repo_with_project_bottom_nav_menu);
}
bottomNavigation.setOnMenuItemClickListener(getPresenter());
if (repoModel.getTopics() != null && !repoModel.getTopics().isEmpty()) {
tagsIcon.setVisibility(View.VISIBLE);
topicsList.setAdapter(new TopicsAdapter(repoModel.getTopics()));
}
 else {
topicsList.setVisibility(View.GONE);
}
onRepoPinned(AbstractPinnedRepos.isPinned(repoModel.getFullName()));
wikiLayout.setVisibility(repoModel.isHasWiki() ? View.VISIBLE : View.GONE);
pinText.setText(R.string.pin);
detailsIcon.setVisibility(InputHelper.isEmpty(repoModel.getDescription()) ? View.GONE : View.VISIBLE);
language.setVisibility(InputHelper.isEmpty(repoModel.getLanguage()) ? View.GONE : View.VISIBLE);
if (!InputHelper.isEmpty(repoModel.getLanguage())) {
language.setText(repoModel.getLanguage());
language.setTextColor(ColorsProvider.getColorAsColor(repoModel.getLanguage(),language.getContext()));
}
forkRepo.setText(numberFormat.format(repoModel.getForksCount()));
starRepo.setText(numberFormat.format(repoModel.getStargazersCount()));
watchRepo.setText(numberFormat.format(repoModel.getSubsCount()));
if (repoModel.getOwner() != null) {
avatarLayout.setUrl(repoModel.getOwner().getAvatarUrl(),repoModel.getOwner().getLogin(),repoModel.getOwner().isOrganizationType(),LinkParserHelper.isEnterprise(repoModel.getHtmlUrl()));
}
 else if (repoModel.getOrganization() != null) {
avatarLayout.setUrl(repoModel.getOrganization().getAvatarUrl(),repoModel.getOrganization().getLogin(),true,LinkParserHelper.isEnterprise(repoModel.getHtmlUrl()));
}
long repoSize=repoModel.getSize() > 0 ? (repoModel.getSize() * 1000) : repoModel.getSize();
date.setText(SpannableBuilder.builder().append(ParseDateFormat.getTimeAgo(repoModel.getPushedAt())).append(" ,").append(" ").append(Formatter.formatFileSize(this,repoSize)));
size.setVisibility(View.GONE);
title.setText(repoModel.getFullName());
TextViewCompat.setTextAppearance(title,R.style.TextAppearance_AppCompat_Medium);
title.setTextColor(ViewHelper.getPrimaryTextColor(this));
if (repoModel.getLicense() != null) {
licenseLayout.setVisibility(View.VISIBLE);
LicenseModel licenseModel=repoModel.getLicense();
license.setText(!InputHelper.isEmpty(licenseModel.getSpdxId()) ? licenseModel.getSpdxId() : licenseModel.getName());
}
supportInvalidateOptionsMenu();
if (!PrefGetter.isRepoGuideShowed()) {
}
onRepoWatched(getPresenter().isWatched());
onRepoStarred(getPresenter().isStarred());
onRepoForked(getPresenter().isForked());
}
