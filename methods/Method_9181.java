private void updateShareButton(){
  if (shareButton == null) {
    return;
  }
  shareButton.setVisibility(isInline || !videoPlayer.isPlayerPrepared() ? GONE : VISIBLE);
}
