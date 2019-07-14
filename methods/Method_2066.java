private void updateRounding(){
  boolean showBorder=mShowBordersCheck.isChecked();
  boolean scaleInsideBorder=showBorder && mScaleInsideBordersCheck.isChecked();
  setShowBorder(mDraweeRound,showBorder,scaleInsideBorder);
  setShowBorder(mDraweeRadius,showBorder,scaleInsideBorder);
  setShowBorder(mDraweeSome,showBorder,scaleInsideBorder);
  setShowBorder(mDraweeSomeRtl,showBorder,scaleInsideBorder);
  setShowBorder(mDraweeFancy,showBorder,scaleInsideBorder);
}
