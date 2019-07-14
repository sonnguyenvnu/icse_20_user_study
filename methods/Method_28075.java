@NonNull @Override public LabelsPresenter providePresenter(){
  Bundle bundle=getArguments();
  return new LabelsPresenter(bundle.getString(BundleConstant.EXTRA_THREE),bundle.getString(BundleConstant.EXTRA_TWO));
}
