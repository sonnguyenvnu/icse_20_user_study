public void setCurrentVideoVisible(boolean visible){
  if (currentAspectRatioFrameLayout == null) {
    return;
  }
  if (visible) {
    if (pipRoundVideoView != null) {
      pipSwitchingState=2;
      pipRoundVideoView.close(true);
      pipRoundVideoView=null;
    }
 else     if (currentAspectRatioFrameLayout != null) {
      if (currentAspectRatioFrameLayout.getParent() == null) {
        currentTextureViewContainer.addView(currentAspectRatioFrameLayout);
      }
      videoPlayer.setTextureView(currentTextureView);
    }
  }
 else {
    if (currentAspectRatioFrameLayout.getParent() != null) {
      pipSwitchingState=1;
      currentTextureViewContainer.removeView(currentAspectRatioFrameLayout);
    }
 else {
      if (pipRoundVideoView == null) {
        try {
          pipRoundVideoView=new PipRoundVideoView();
          pipRoundVideoView.show(baseActivity,() -> cleanupPlayer(true,true));
        }
 catch (        Exception e) {
          pipRoundVideoView=null;
        }
      }
      if (pipRoundVideoView != null) {
        videoPlayer.setTextureView(pipRoundVideoView.getTextureView());
      }
    }
  }
}
