private void getSelectedDxDy(float[] outPosition){
  if ((mSelectedFlags & (LEFT | RIGHT)) != 0) {
    outPosition[0]=mSelectedStartX + mDx - mSelected.itemView.getLeft();
  }
 else {
    outPosition[0]=mSelected.itemView.getTranslationX();
  }
  if ((mSelectedFlags & (UP | DOWN)) != 0) {
    outPosition[1]=mSelectedStartY + mDy - mSelected.itemView.getTop();
  }
 else {
    outPosition[1]=mSelected.itemView.getTranslationY();
  }
}
