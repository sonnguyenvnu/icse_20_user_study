public void setTextureView(TextureView textureView,AspectRatioFrameLayout aspectRatioFrameLayout,FrameLayout container,boolean set){
  if (textureView == null) {
    return;
  }
  if (!set && currentTextureView == textureView) {
    pipSwitchingState=1;
    currentTextureView=null;
    currentAspectRatioFrameLayout=null;
    currentTextureViewContainer=null;
    return;
  }
  if (videoPlayer == null || textureView == currentTextureView) {
    return;
  }
  isDrawingWasReady=aspectRatioFrameLayout != null && aspectRatioFrameLayout.isDrawingReady();
  currentTextureView=textureView;
  if (pipRoundVideoView != null) {
    videoPlayer.setTextureView(pipRoundVideoView.getTextureView());
  }
 else {
    videoPlayer.setTextureView(currentTextureView);
  }
  currentAspectRatioFrameLayout=aspectRatioFrameLayout;
  currentTextureViewContainer=container;
  if (currentAspectRatioFrameLayoutReady && currentAspectRatioFrameLayout != null) {
    if (currentAspectRatioFrameLayout != null) {
      currentAspectRatioFrameLayout.setAspectRatio(currentAspectRatioFrameLayoutRatio,currentAspectRatioFrameLayoutRotation);
    }
  }
}
