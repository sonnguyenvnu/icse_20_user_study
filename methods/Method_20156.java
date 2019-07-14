/** 
 * Intelligently notify item changes by comparing the current  {@link #models} list against theprevious so you don't have to micromanage notification calls yourself. This may be prohibitively slow for large model lists (in the hundreds), in which case consider doing notification calls yourself. If you use this, all your view models must implement  {@link EpoxyModel#hashCode()} and {@link EpoxyModel#equals(Object)} to completely identify theirstate, so that changes to a model's content can be detected. Before using this you must enable it with  {@link #enableDiffing()}, since keeping track of the model state adds extra computation time to all other data change notifications.
 * @see #enableDiffing()
 */
protected void notifyModelsChanged(){
  if (diffHelper == null) {
    throw new IllegalStateException("You must enable diffing before notifying models changed");
  }
  diffHelper.notifyModelChanges();
}
