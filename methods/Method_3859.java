View findOnePartiallyOrCompletelyInvisibleChild(int fromIndex,int toIndex){
  ensureLayoutState();
  final int next=toIndex > fromIndex ? 1 : (toIndex < fromIndex ? -1 : 0);
  if (next == 0) {
    return getChildAt(fromIndex);
  }
  @ViewBoundsCheck.ViewBounds int preferredBoundsFlag=0;
  @ViewBoundsCheck.ViewBounds int acceptableBoundsFlag=0;
  if (mOrientationHelper.getDecoratedStart(getChildAt(fromIndex)) < mOrientationHelper.getStartAfterPadding()) {
    preferredBoundsFlag=(ViewBoundsCheck.FLAG_CVS_LT_PVS | ViewBoundsCheck.FLAG_CVE_LT_PVE | ViewBoundsCheck.FLAG_CVE_GT_PVS);
    acceptableBoundsFlag=(ViewBoundsCheck.FLAG_CVS_LT_PVS | ViewBoundsCheck.FLAG_CVE_LT_PVE);
  }
 else {
    preferredBoundsFlag=(ViewBoundsCheck.FLAG_CVE_GT_PVE | ViewBoundsCheck.FLAG_CVS_GT_PVS | ViewBoundsCheck.FLAG_CVS_LT_PVE);
    acceptableBoundsFlag=(ViewBoundsCheck.FLAG_CVE_GT_PVE | ViewBoundsCheck.FLAG_CVS_GT_PVS);
  }
  return (mOrientation == HORIZONTAL) ? mHorizontalBoundCheck.findOneViewWithinBoundFlags(fromIndex,toIndex,preferredBoundsFlag,acceptableBoundsFlag) : mVerticalBoundCheck.findOneViewWithinBoundFlags(fromIndex,toIndex,preferredBoundsFlag,acceptableBoundsFlag);
}
