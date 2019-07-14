/** 
 * Opens the camera driver and initializes the hardware parameters.
 * @param holder The surface object which the camera will draw preview frames into.
 * @throws IOException Indicates the camera driver failed to open.
 */
public void openDriver(SurfaceHolder holder) throws IOException {
  if (camera == null) {
    camera=Camera.open();
    if (camera == null) {
      throw new IOException();
    }
    camera.setPreviewDisplay(holder);
    if (!initialized) {
      initialized=true;
      configManager.initFromCameraParameters(camera);
    }
    configManager.setDesiredCameraParameters(camera);
    FlashlightManager.enableFlashlight();
  }
}
