private void drawShadowCorners(Canvas canvas,Rect bounds){
  int saved=canvas.save();
  if (!mHideTopShadow) {
    canvas.translate(bounds.left,bounds.top);
    canvas.drawPath(mCornerShadowTopPath,mCornerShadowPaint);
    canvas.restoreToCount(saved);
    saved=canvas.save();
    canvas.translate(bounds.right,bounds.top);
    canvas.scale(-1f,1f);
    canvas.drawPath(mCornerShadowTopPath,mCornerShadowPaint);
    canvas.restoreToCount(saved);
  }
  if (!mHideBottomShadow) {
    saved=canvas.save();
    canvas.translate(bounds.right,bounds.bottom);
    canvas.scale(-1f,-1f);
    canvas.drawPath(mCornerShadowBottomPath,mCornerShadowPaint);
    canvas.restoreToCount(saved);
    saved=canvas.save();
    canvas.translate(bounds.left,bounds.bottom);
    canvas.scale(1f,-1f);
    canvas.drawPath(mCornerShadowBottomPath,mCornerShadowPaint);
    canvas.restoreToCount(saved);
  }
}
