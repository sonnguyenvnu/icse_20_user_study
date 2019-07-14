private void updateFullscreenState(boolean byButton){
  if (textureView == null) {
    return;
  }
  updateFullscreenButton();
  if (textureViewContainer == null) {
    changingTextureView=true;
    if (!inFullscreen) {
      if (textureViewContainer != null) {
        textureViewContainer.addView(textureView);
      }
 else {
        aspectRatioFrameLayout.addView(textureView);
      }
    }
    if (inFullscreen) {
      ViewGroup viewGroup=(ViewGroup)controlsView.getParent();
      if (viewGroup != null) {
        viewGroup.removeView(controlsView);
      }
    }
 else {
      ViewGroup parent=(ViewGroup)controlsView.getParent();
      if (parent != this) {
        if (parent != null) {
          parent.removeView(controlsView);
        }
        if (textureViewContainer != null) {
          textureViewContainer.addView(controlsView);
        }
 else {
          addView(controlsView,1);
        }
      }
    }
    changedTextureView=delegate.onSwitchToFullscreen(controlsView,inFullscreen,aspectRatioFrameLayout.getAspectRatio(),aspectRatioFrameLayout.getVideoRotation(),byButton);
    changedTextureView.setVisibility(INVISIBLE);
    if (inFullscreen && changedTextureView != null) {
      ViewGroup parent=(ViewGroup)textureView.getParent();
      if (parent != null) {
        parent.removeView(textureView);
      }
    }
    controlsView.checkNeedHide();
  }
 else {
    if (inFullscreen) {
      ViewGroup viewGroup=(ViewGroup)aspectRatioFrameLayout.getParent();
      if (viewGroup != null) {
        viewGroup.removeView(aspectRatioFrameLayout);
      }
    }
 else {
      ViewGroup parent=(ViewGroup)aspectRatioFrameLayout.getParent();
      if (parent != this) {
        if (parent != null) {
          parent.removeView(aspectRatioFrameLayout);
        }
        addView(aspectRatioFrameLayout,0);
      }
    }
    delegate.onSwitchToFullscreen(controlsView,inFullscreen,aspectRatioFrameLayout.getAspectRatio(),aspectRatioFrameLayout.getVideoRotation(),byButton);
  }
}
