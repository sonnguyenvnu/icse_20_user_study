private boolean isFaceAnchorOccupied(PhotoFace face,int anchor,long documentId,TLRPC.TL_maskCoords maskCoords){
  Point anchorPoint=face.getPointForAnchor(anchor);
  if (anchorPoint == null) {
    return true;
  }
  float minDistance=face.getWidthForAnchor(0) * 1.1f;
  for (int index=0; index < entitiesView.getChildCount(); index++) {
    View view=entitiesView.getChildAt(index);
    if (!(view instanceof StickerView)) {
      continue;
    }
    StickerView stickerView=(StickerView)view;
    if (stickerView.getAnchor() != anchor) {
      continue;
    }
    Point location=stickerView.getPosition();
    float distance=(float)Math.hypot(location.x - anchorPoint.x,location.y - anchorPoint.y);
    if ((documentId == stickerView.getSticker().id || faces.size() > 1) && distance < minDistance) {
      return true;
    }
  }
  return false;
}
