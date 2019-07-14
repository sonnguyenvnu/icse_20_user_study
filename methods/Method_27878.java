@Override protected void onFragmentCreated(@NonNull View view,@Nullable Bundle savedInstanceState){
  onInitOrgs(getPresenter().getOrgs());
  onInitPinnedRepos(getPresenter().getNodes());
  if (savedInstanceState == null) {
    getPresenter().onFragmentCreated(getArguments());
  }
 else {
    if (userModel != null) {
      invalidateFollowBtn();
      onInitViews(userModel);
    }
 else {
      getPresenter().onFragmentCreated(getArguments());
    }
  }
  if (isMeOrOrganization()) {
    followBtn.setVisibility(GONE);
  }
}
