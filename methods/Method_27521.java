/** 
 * not really needed but meh.
 */
private void clearCachedComments(){
  if (this instanceof IssuePagerActivity || this instanceof CommitPagerActivity || this instanceof PullRequestPagerActivity || this instanceof GistActivity) {
    CachedComments.Companion.getInstance().clear();
  }
}
