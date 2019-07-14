@Override protected int getChildDrawingOrder(int childCount,int i){
  updateChildDrawingOrderIfNeeded();
  if (mDispatchDraw.isRunning()) {
    mDispatchDraw.drawNext();
  }
  return mChildDrawingOrder[i];
}
