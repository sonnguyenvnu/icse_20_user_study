@Override public void onRefresh(){
  getPresenter().onCallApi(1,getArguments().getLong(BundleConstant.EXTRA));
}
