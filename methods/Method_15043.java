/** 
 * Reads, one time, values from the camera that are needed by the app.
 */
void initFromCameraParameters(Camera camera){
  Camera.Parameters parameters=camera.getParameters();
  previewFormat=parameters.getPreviewFormat();
  previewFormatString=parameters.get("preview-format");
  Log.d(TAG,"Default preview format: " + previewFormat + '/' + previewFormatString);
  WindowManager manager=(WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
  Display display=manager.getDefaultDisplay();
  screenResolution=new Point(display.getWidth(),display.getHeight());
  Log.d(TAG,"Screen resolution: " + screenResolution);
  cameraResolution=getCameraResolution(parameters,screenResolution);
  Log.d(TAG,"Camera resolution: " + screenResolution);
}
