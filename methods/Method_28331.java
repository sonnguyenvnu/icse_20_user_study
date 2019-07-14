@Override public void onFragmentCreated(@Nullable Bundle bundle){
  if (bundle != null) {
    repoId=bundle.getString(BundleConstant.EXTRA);
    login=bundle.getString(BundleConstant.EXTRA_TWO);
    id=bundle.getLong(BundleConstant.ID);
    reactionType=(ReactionTypes)bundle.getSerializable(BundleConstant.EXTRA_TYPE);
    reactionTypeMode=bundle.getInt(BundleConstant.EXTRA_THREE);
    onCallApi(1,null);
  }
}
