private void drawLine(Canvas canvas,RectF rectFrom,RectF rectTo){
  pGraph.setColor(primaryColor);
  canvas.drawLine(rectFrom.centerX(),rectFrom.centerY(),rectTo.centerX(),rectTo.centerY(),pGraph);
}
