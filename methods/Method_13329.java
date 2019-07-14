/** 
 * Sets whether "mark all" highlights are shown in this error strip.
 * @param show Whether to show markers for "mark all" highlights.
 * @see #getShowMarkAll()
 */
public void setShowMarkAll(boolean show){
  if (show != showMarkAll) {
    showMarkAll=show;
    if (isDisplayable()) {
      refreshMarkers();
    }
  }
}
