@Override public void onRefresh(){
  getPresenter().onCallApi(1,getArguments().getString(BundleConstant.EXTRA));
}
