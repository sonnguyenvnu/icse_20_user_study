/** 
 * ?????
 * @return
 */
public static void closeFlashLight(){
  try {
    if (camera == null) {
    }
 else {
      Camera.Parameters parameters=camera.getParameters();
      parameters.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
      camera.setParameters(parameters);
      camera.release();
      camera=null;
    }
  }
 catch (  Exception e) {
    e.printStackTrace();
  }
}
