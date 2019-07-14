public void onPause(){
  if (shutterButton == null) {
    return;
  }
  if (!requestingPermissions) {
    if (cameraView != null && shutterButton.getState() == ShutterButton.State.RECORDING) {
      resetRecordState();
      CameraController.getInstance().stopVideoRecording(cameraView.getCameraSession(),false);
      shutterButton.setState(ShutterButton.State.DEFAULT,true);
    }
    if (cameraOpened) {
      closeCamera(false);
    }
    hideCamera(true);
  }
 else {
    if (cameraView != null && shutterButton.getState() == ShutterButton.State.RECORDING) {
      shutterButton.setState(ShutterButton.State.DEFAULT,true);
    }
    requestingPermissions=false;
  }
  paused=true;
}
