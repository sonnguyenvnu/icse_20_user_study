/** 
 * Set scale, center and orientation from saved state.
 */
private void restoreState(ImageViewState state){
  if (state != null && state.getCenter() != null && VALID_ORIENTATIONS.contains(state.getOrientation())) {
    this.orientation=state.getOrientation();
    this.pendingScale=state.getScale();
    this.sPendingCenter=state.getCenter();
    invalidate();
  }
}
