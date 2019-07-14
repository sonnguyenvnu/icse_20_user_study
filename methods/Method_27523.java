private void initEnterpriseExtra(@Nullable Bundle savedInstanceState){
  if (savedInstanceState == null) {
    if (getIntent() != null) {
      if (getIntent().getExtras() != null) {
        getPresenter().setEnterprise(getIntent().getExtras().getBoolean(BundleConstant.IS_ENTERPRISE));
      }
 else       if (getIntent().hasExtra(BundleConstant.IS_ENTERPRISE)) {
        getPresenter().setEnterprise(getIntent().getBooleanExtra(BundleConstant.IS_ENTERPRISE,false));
      }
    }
  }
}
