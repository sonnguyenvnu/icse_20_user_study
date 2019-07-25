/** 
 * Bring window to front is stage exists.
 * @return
 */
protected boolean staged(){
  if (stage != null) {
    stage.toFront();
    if (!stage.isShowing()) {
      stage.show();
    }
    return true;
  }
  return false;
}
