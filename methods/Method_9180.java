private void updateFullscreenButton(){
  if (!videoPlayer.isPlayerPrepared() || isInline) {
    fullscreenButton.setVisibility(GONE);
    return;
  }
  fullscreenButton.setVisibility(VISIBLE);
  if (!inFullscreen) {
    fullscreenButton.setImageResource(R.drawable.ic_gofullscreen);
    fullscreenButton.setLayoutParams(LayoutHelper.createFrame(56,56,Gravity.RIGHT | Gravity.BOTTOM,0,0,0,5));
  }
 else {
    fullscreenButton.setImageResource(R.drawable.ic_outfullscreen);
    fullscreenButton.setLayoutParams(LayoutHelper.createFrame(56,56,Gravity.RIGHT | Gravity.BOTTOM,0,0,0,1));
  }
}
