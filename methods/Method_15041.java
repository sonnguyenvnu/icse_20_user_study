/** 
 * ????????
 * @param open
 */
protected void switchLight(boolean on){
  if (on == isOn()) {
    return;
  }
  isOn=CameraManager.get().switchLight(on);
}
