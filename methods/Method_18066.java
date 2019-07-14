private static void drawMountBoundsCorners(Canvas canvas,Paint paint,Rect bounds,int cornerLength,int cornerWidth){
  drawCorner(canvas,paint,bounds.left,bounds.top,cornerLength,cornerLength,cornerWidth);
  drawCorner(canvas,paint,bounds.left,bounds.bottom,cornerLength,-cornerLength,cornerWidth);
  drawCorner(canvas,paint,bounds.right,bounds.top,-cornerLength,cornerLength,cornerWidth);
  drawCorner(canvas,paint,bounds.right,bounds.bottom,-cornerLength,-cornerLength,cornerWidth);
}
