private void callApi(){
  getPresenter().onCallApi(getArguments().getString(BundleConstant.EXTRA),getArguments().getString(BundleConstant.ID),getArguments().getBoolean(BundleConstant.EXTRA_TWO));
}
