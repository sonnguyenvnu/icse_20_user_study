@Override public void onPatchClicked(int groupPosition,int childPosition,View v,CommitFileModel commit,CommitLinesModel item){
  if (item.getText().startsWith("@@"))   return;
  if (PrefGetter.isProEnabled()) {
    AddReviewDialogFragment.Companion.newInstance(item,Bundler.start().put(BundleConstant.ITEM,commit.getBlobUrl()).put(BundleConstant.EXTRA,commit.getFilename()).end()).show(getChildFragmentManager(),"AddReviewDialogFragment");
  }
 else {
    PremiumActivity.Companion.startActivity(getContext());
  }
}
