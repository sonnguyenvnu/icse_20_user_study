@Override public void onFragmentCreated(@NonNull Bundle argument){
  user=argument.getString(BundleConstant.EXTRA);
  isOrg=argument.getBoolean(BundleConstant.EXTRA_TWO);
  if (eventsModels.isEmpty()) {
    onCallApi(1);
  }
}
