@Override public void onTransitionAnimationEnd(boolean isOpen,boolean backward){
  if (isOpen) {
    try {
      if (mapView.getParent() instanceof ViewGroup) {
        ViewGroup viewGroup=(ViewGroup)mapView.getParent();
        viewGroup.removeView(mapView);
      }
    }
 catch (    Exception e) {
      FileLog.e(e);
    }
    if (mapViewClip != null) {
      mapViewClip.addView(mapView,0,LayoutHelper.createFrame(LayoutHelper.MATCH_PARENT,overScrollHeight + AndroidUtilities.dp(10),Gravity.TOP | Gravity.LEFT));
      updateClipView(layoutManager.findFirstVisibleItemPosition());
    }
 else     if (fragmentView != null) {
      ((FrameLayout)fragmentView).addView(mapView,0,LayoutHelper.createFrame(LayoutHelper.MATCH_PARENT,LayoutHelper.MATCH_PARENT,Gravity.TOP | Gravity.LEFT));
    }
  }
}
