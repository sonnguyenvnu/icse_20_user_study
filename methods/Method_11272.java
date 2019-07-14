public void goCabinPosition(CabinPosition mCabinPosition){
  if (mAnimatedValue > 0) {
    if (mCabinPosition == CabinPosition.Top) {
      moveY=0;
    }
 else     if (mCabinPosition == CabinPosition.Last) {
      moveY=rectFCabin.height() - rectFCabin.width() * 2.5f;
    }
 else     if (mCabinPosition == CabinPosition.Middle) {
      moveY=(rectFCabin.height() - rectFCabin.width() * 2.5f) / 2;
    }
    invalidate();
  }
}
