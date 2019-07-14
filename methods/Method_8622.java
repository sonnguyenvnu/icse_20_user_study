@Override protected boolean onCustomMeasure(View view,int width,int height){
  if (view == videoView.getControlsView()) {
    ViewGroup.LayoutParams layoutParams=view.getLayoutParams();
    layoutParams.width=videoView.getMeasuredWidth();
    layoutParams.height=videoView.getAspectRatioView().getMeasuredHeight() + (videoView.isInFullscreen() ? 0 : AndroidUtilities.dp(10));
  }
  return false;
}
