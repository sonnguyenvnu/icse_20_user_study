@OnClick(R.id.branches) void onBranchesClicked(){
  BranchesPagerFragment.Companion.newInstance(getPresenter().login,getPresenter().repoId).show(getChildFragmentManager(),"BranchesFragment");
}
