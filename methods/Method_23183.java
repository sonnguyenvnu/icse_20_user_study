private void setCanvasSize(){
  int contentW=Math.max(sketchWidth,MIN_WINDOW_WIDTH);
  int contentH=Math.max(sketchHeight,MIN_WINDOW_HEIGHT);
  canvas.setBounds((contentW - sketchWidth) / 2,(contentH - sketchHeight) / 2,sketchWidth,sketchHeight);
}
