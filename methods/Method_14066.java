public static AffineTransform createRotationMatrix(int rotation,double l){
  if (rotation == ScatterplotFacet.ROTATE_CW) {
    AffineTransform t=AffineTransform.getTranslateInstance(0,l / 2);
    t.scale(s_rotateScale,s_rotateScale);
    t.rotate(-Math.PI / 4);
    return t;
  }
 else   if (rotation == ScatterplotFacet.ROTATE_CCW) {
    AffineTransform t=AffineTransform.getTranslateInstance(l / 2,0);
    t.scale(s_rotateScale,s_rotateScale);
    t.rotate(Math.PI / 4);
    return t;
  }
 else {
    return null;
  }
}
