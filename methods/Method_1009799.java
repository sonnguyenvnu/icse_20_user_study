public static RESConfig build(Context context,StreamAVOption option){
  RESConfig res=RESConfig.obtain();
  res.setFilterMode(DEFAULT_FILTER_MODE);
  res.setRenderingMode(DEFAULT_RENDER_MODE);
  res.setTargetPreviewSize(new Size(option.previewWidth,option.previewHeight));
  res.setTargetVideoSize(new Size(option.videoWidth,option.videoHeight));
  res.setBitRate(option.videoBitrate);
  res.setVideoFPS(option.videoFramerate);
  res.setVideoGOP(option.videoGOP);
  res.setDefaultCamera(option.cameraIndex);
  res.setRtmpAddr(option.streamUrl);
  int frontDirection, backDirection;
  Camera.CameraInfo cameraInfo=new Camera.CameraInfo();
  Camera.getCameraInfo(Camera.CameraInfo.CAMERA_FACING_FRONT,cameraInfo);
  frontDirection=cameraInfo.orientation;
  Camera.getCameraInfo(Camera.CameraInfo.CAMERA_FACING_BACK,cameraInfo);
  backDirection=cameraInfo.orientation;
  if (context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
    res.setFrontCameraDirectionMode((frontDirection == 90 ? RESConfig.DirectionMode.FLAG_DIRECTION_ROATATION_270 : RESConfig.DirectionMode.FLAG_DIRECTION_ROATATION_90) | RESConfig.DirectionMode.FLAG_DIRECTION_FLIP_HORIZONTAL);
    res.setBackCameraDirectionMode((backDirection == 90 ? RESConfig.DirectionMode.FLAG_DIRECTION_ROATATION_90 : RESConfig.DirectionMode.FLAG_DIRECTION_ROATATION_270));
  }
 else {
    res.setBackCameraDirectionMode((backDirection == 90 ? RESConfig.DirectionMode.FLAG_DIRECTION_ROATATION_0 : RESConfig.DirectionMode.FLAG_DIRECTION_ROATATION_180));
    res.setFrontCameraDirectionMode((frontDirection == 90 ? RESConfig.DirectionMode.FLAG_DIRECTION_ROATATION_180 : RESConfig.DirectionMode.FLAG_DIRECTION_ROATATION_0) | RESConfig.DirectionMode.FLAG_DIRECTION_FLIP_HORIZONTAL);
  }
  return res;
}
