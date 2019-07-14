@Override public void onAddNewFile(){
  Logger.e("Hello world");
  if (adapter.getItemCount() == 0 || (PrefGetter.isProEnabled() || PrefGetter.isAllFeaturesUnlocked())) {
    AddGistBottomSheetDialog.Companion.newInstance(null,-1).show(getChildFragmentManager(),AddGistBottomSheetDialog.Companion.getTAG());
  }
 else {
    PremiumActivity.Companion.startActivity(getContext());
  }
}
