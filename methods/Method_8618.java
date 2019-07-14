@Override protected boolean canDismissWithSwipe(){
  return videoView.getVisibility() != View.VISIBLE || !videoView.isInFullscreen();
}
