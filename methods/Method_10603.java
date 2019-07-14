/** 
 * Closes the camera driver if still in use.
 */
public void closeDriver(){
  if (camera != null) {
    FlashlightManager.disableFlashlight();
    camera.release();
    camera=null;
  }
}
