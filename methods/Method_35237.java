/** 
 * Sets the maximum number of pages that will have their states saved. When this number is exceeded, the page that was state saved least recently will have its state removed from the save data.
 */
public void setMaxPagesToStateSave(int maxPagesToStateSave){
  if (maxPagesToStateSave < 0) {
    throw new IllegalArgumentException("Only positive integers may be passed for maxPagesToStateSave.");
  }
  this.maxPagesToStateSave=maxPagesToStateSave;
  ensurePagesSaved();
}
