/** 
 * ?????
 * @param open
 * @return
 */
public boolean switchLight(boolean open){
  parameter=camera.getParameters();
  if (open) {
    parameter.setFlashMode(Parameters.FLASH_MODE_TORCH);
    camera.setParameters(parameter);
    return true;
  }
 else {
    parameter.setFlashMode(Parameters.FLASH_MODE_OFF);
    camera.setParameters(parameter);
    return false;
  }
}
