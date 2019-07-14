private StickerPosition calculateStickerPosition(TLRPC.Document document){
  TLRPC.TL_maskCoords maskCoords=null;
  for (int a=0; a < document.attributes.size(); a++) {
    TLRPC.DocumentAttribute attribute=document.attributes.get(a);
    if (attribute instanceof TLRPC.TL_documentAttributeSticker) {
      maskCoords=attribute.mask_coords;
      break;
    }
  }
  StickerPosition defaultPosition=new StickerPosition(centerPositionForEntity(),0.75f,0.0f);
  if (maskCoords == null || faces == null || faces.size() == 0) {
    return defaultPosition;
  }
 else {
    int anchor=maskCoords.n;
    PhotoFace face=getRandomFaceWithVacantAnchor(anchor,document.id,maskCoords);
    if (face == null) {
      return defaultPosition;
    }
    Point referencePoint=face.getPointForAnchor(anchor);
    float referenceWidth=face.getWidthForAnchor(anchor);
    float angle=face.getAngle();
    Size baseSize=baseStickerSize();
    float scale=(float)(referenceWidth / baseSize.width * maskCoords.zoom);
    float radAngle=(float)Math.toRadians(angle);
    float xCompX=(float)(Math.sin(Math.PI / 2.0f - radAngle) * referenceWidth * maskCoords.x);
    float xCompY=(float)(Math.cos(Math.PI / 2.0f - radAngle) * referenceWidth * maskCoords.x);
    float yCompX=(float)(Math.cos(Math.PI / 2.0f + radAngle) * referenceWidth * maskCoords.y);
    float yCompY=(float)(Math.sin(Math.PI / 2.0f + radAngle) * referenceWidth * maskCoords.y);
    float x=referencePoint.x + xCompX + yCompX;
    float y=referencePoint.y + xCompY + yCompY;
    return new StickerPosition(new Point(x,y),scale,angle);
  }
}
