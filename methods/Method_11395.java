private void realignAndClipCanvasForGravity(Canvas canvas){
  final float currentWidth=columnManager.getCurrentWidth();
  final float currentHeight=metrics.getCharHeight();
  realignAndClipCanvasForGravity(canvas,gravity,viewBounds,currentWidth,currentHeight);
}
