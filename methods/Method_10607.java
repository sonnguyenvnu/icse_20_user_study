public void openLight(){
  if (camera != null) {
    parameter=camera.getParameters();
    parameter.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
    camera.setParameters(parameter);
  }
}
