@Override public void onBranchChanged(@NonNull String branch){
  if (!TextUtils.equals(branch,this.branch)) {
    this.branch=branch;
    onCallApi(1,null);
    getCommitCount(branch);
  }
}
