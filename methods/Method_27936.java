@Override public void onFragmentCreated(@Nullable Bundle bundle){
  if (bundle == null)   throw new NullPointerException("Bundle is null?");
  repoId=bundle.getString(BundleConstant.ID);
  login=bundle.getString(BundleConstant.EXTRA);
  sha=bundle.getString(BundleConstant.EXTRA_TWO);
}
