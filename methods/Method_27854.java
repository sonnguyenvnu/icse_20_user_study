@Override public void onFilterApply(){
  getPresenter().onFilterApply(getArguments().getString(BundleConstant.EXTRA));
}
