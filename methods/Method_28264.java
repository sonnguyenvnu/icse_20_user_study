@Override public boolean isMergeable(){
  return getPullRequest() != null && getPullRequest().isMergeable() && !getPullRequest().isMerged();
}
