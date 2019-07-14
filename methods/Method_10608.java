public void offLight(){
  if (camera != null) {
    parameter=camera.getParameters();
    parameter.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
    camera.setParameters(parameter);
  }
}
