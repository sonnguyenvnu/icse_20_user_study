@Override protected boolean onCustomLayout(View view,int left,int top,int right,int bottom){
  if (view == videoView.getControlsView()) {
    updateTextureViewPosition();
  }
  return false;
}
