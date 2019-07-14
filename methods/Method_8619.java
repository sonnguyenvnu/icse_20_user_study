public void updateTextureViewPosition(){
  View view=videoView.getAspectRatioView();
  view.getLocationInWindow(position);
  position[0]-=getLeftInset();
  if (!videoView.isInline() && !animationInProgress) {
    TextureView textureView=videoView.getTextureView();
    textureView.setTranslationX(position[0]);
    textureView.setTranslationY(position[1]);
    View textureImageView=videoView.getTextureImageView();
    if (textureImageView != null) {
      textureImageView.setTranslationX(position[0]);
      textureImageView.setTranslationY(position[1]);
    }
  }
  View controlsView=videoView.getControlsView();
  if (controlsView.getParent() == container) {
    controlsView.setTranslationY(position[1]);
  }
 else {
    controlsView.setTranslationY(0);
  }
}
