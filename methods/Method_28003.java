@Override public void setUserVisibleHint(boolean isVisibleToUser){
  super.setUserVisibleHint(isVisibleToUser);
  if (isSafe() && getRepoFilesView() != null)   getRepoFilesView().onHiddenChanged(!isVisibleToUser);
}
