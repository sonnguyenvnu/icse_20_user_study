private void updatePointersOnMove(MotionEvent event){
  for (int i=0; i < MAX_POINTERS; i++) {
    int index=event.findPointerIndex(mId[i]);
    if (index != -1) {
      mCurrentX[i]=event.getX(index);
      mCurrentY[i]=event.getY(index);
    }
  }
}
