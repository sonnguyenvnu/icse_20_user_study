@Override protected void onFragmentCreated(@NonNull View view,@Nullable Bundle savedInstanceState){
  if (InputHelper.isEmpty(getPresenter().downloadedStream())) {
    getPresenter().onHandleIntent(getArguments());
  }
 else {
    if (getPresenter().isMarkDown()) {
      onSetMdText(getPresenter().downloadedStream(),getPresenter().url(),false);
    }
 else {
      onSetCode(getPresenter().downloadedStream());
    }
  }
  getActivity().invalidateOptionsMenu();
  stateLayout.setEmptyText(R.string.no_data);
  if (savedInstanceState == null) {
    stateLayout.showReload(0);
  }
  stateLayout.setOnReloadListener(view1 -> getPresenter().onHandleIntent(getArguments()));
  if (getPresenter().isRepo()) {
    appBarLayout=getActivity().findViewById(R.id.appbar);
    bottomNavigation=getActivity().findViewById(R.id.bottomNavigation);
    if (appBarLayout != null && !isAppBarListener) {
      appBarLayout.addOnOffsetChangedListener(this);
      isAppBarListener=true;
    }
  }
}
