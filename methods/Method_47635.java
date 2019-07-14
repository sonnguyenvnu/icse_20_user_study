private void drawMarker(Canvas canvas,RectF rect,Integer value){
  float padding=rect.height() * 0.2f;
  float maxRadius=(rect.height() - 2 * padding) / 2.0f;
  float scale=1.0f / maxFreq * value;
  float radius=maxRadius * scale;
  int colorIndex=Math.min(colors.length - 1,Math.round((colors.length - 1) * scale));
  pGraph.setColor(colors[colorIndex]);
  canvas.drawCircle(rect.centerX(),rect.centerY(),radius,pGraph);
}
