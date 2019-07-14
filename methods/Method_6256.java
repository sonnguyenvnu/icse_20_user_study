protected void focusToRect(Rect focusRect,Rect meteringRect){
  try {
    Camera camera=cameraInfo.camera;
    if (camera != null) {
      camera.cancelAutoFocus();
      Camera.Parameters parameters=null;
      try {
        parameters=camera.getParameters();
      }
 catch (      Exception e) {
        FileLog.e(e);
      }
      if (parameters != null) {
        parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_AUTO);
        ArrayList<Camera.Area> meteringAreas=new ArrayList<>();
        meteringAreas.add(new Camera.Area(focusRect,1000));
        parameters.setFocusAreas(meteringAreas);
        if (meteringAreaSupported) {
          meteringAreas=new ArrayList<>();
          meteringAreas.add(new Camera.Area(meteringRect,1000));
          parameters.setMeteringAreas(meteringAreas);
        }
        try {
          camera.setParameters(parameters);
          camera.autoFocus(autoFocusCallback);
        }
 catch (        Exception e) {
          FileLog.e(e);
        }
      }
    }
  }
 catch (  Exception e) {
    FileLog.e(e);
  }
}
