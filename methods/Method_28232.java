@Override public void onPatchClicked(int groupPosition,int childPosition,View v,CommitFileModel commit,CommitLinesModel item){
  if (item.getText().startsWith("@@"))   return;
  if (PrefGetter.isProEnabled()) {
    AddReviewDialogFragment.Companion.newInstance(item,Bundler.start().put(BundleConstant.ITEM,commit.getFilename()).put(BundleConstant.EXTRA_TWO,groupPosition).put(BundleConstant.EXTRA_THREE,childPosition).end()).show(getChildFragmentManager(),"AddReviewDialogFragment");
  }
 else {
    PremiumActivity.Companion.startActivity(getContext());
  }
}
