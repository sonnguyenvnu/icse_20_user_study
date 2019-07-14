private void initPresenterBundle(@Nullable Bundle savedInstanceState){
  if (savedInstanceState != null && !savedInstanceState.isEmpty()) {
    StateSaver.restoreInstanceState(this,savedInstanceState);
    getPresenter().onRestoreInstanceState(presenterStateBundle);
  }
}
