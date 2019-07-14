private void drawHand(Canvas canvas,int degrees,int length,Paint paint){
  final int savedCount=canvas.save();
  canvas.rotate(degrees,mCenterX,mCenterY);
  final int startX=mCenterX;
  final int startY=mCenterY - mCenterDotRadius;
  final int endX=mCenterX;
  final int endY=startY - length;
  canvas.drawLine(startX,startY,endX,endY,paint);
  canvas.drawCircle(endX,endY,0.001f,paint);
  canvas.restoreToCount(savedCount);
}
