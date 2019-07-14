@OnClick(R.id.add) void onAddLabel(){
  String repo=getArguments().getString(BundleConstant.EXTRA_TWO);
  String login=getArguments().getString(BundleConstant.EXTRA_THREE);
  if (!InputHelper.isEmpty(repo) && !InputHelper.isEmpty(login)) {
    CreateLabelDialogFragment.newInstance(login,repo).show(getChildFragmentManager(),"CreateLabelDialogFragment");
  }
}
