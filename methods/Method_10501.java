private static void handleFocusMetering(MotionEvent event,Camera camera){
  Log.e("Camera","??handleFocusMetering");
  Camera.Parameters params=camera.getParameters();
  Camera.Size previewSize=params.getPreviewSize();
  Rect focusRect=calculateTapArea(event.getX(),event.getY(),1f,previewSize);
  Rect meteringRect=calculateTapArea(event.getX(),event.getY(),1.5f,previewSize);
  camera.cancelAutoFocus();
  if (params.getMaxNumFocusAreas() > 0) {
    List<Camera.Area> focusAreas=new ArrayList<>();
    focusAreas.add(new Camera.Area(focusRect,800));
    params.setFocusAreas(focusAreas);
  }
 else {
    Log.i(TAG,"focus areas not supported");
  }
  if (params.getMaxNumMeteringAreas() > 0) {
    List<Camera.Area> meteringAreas=new ArrayList<>();
    meteringAreas.add(new Camera.Area(meteringRect,800));
    params.setMeteringAreas(meteringAreas);
  }
 else {
    Log.i(TAG,"metering areas not supported");
  }
  final String currentFocusMode=params.getFocusMode();
  params.setFocusMode(Camera.Parameters.FOCUS_MODE_MACRO);
  camera.setParameters(params);
  camera.autoFocus(new Camera.AutoFocusCallback(){
    @Override public void onAutoFocus(    boolean success,    Camera camera){
      Camera.Parameters params=camera.getParameters();
      params.setFocusMode(currentFocusMode);
      camera.setParameters(params);
    }
  }
);
}
