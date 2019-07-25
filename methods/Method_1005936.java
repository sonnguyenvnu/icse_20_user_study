static void start(int resultCode,Intent data){
  mediaProjectionCallback=new MediaProjectionCallback();
  mediaProjection=projectionManager.getMediaProjection(resultCode,data);
  mediaProjection.registerCallback(mediaProjectionCallback,null);
  virtualDisplay=createVirtualDisplay();
  mediaRecorder.start();
  setRecording(true);
}
