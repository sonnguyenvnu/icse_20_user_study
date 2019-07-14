private void prepareForMoving(MotionEvent ev){
  maybeStartTracking=false;
  startedTracking=true;
  startedTrackingX=(int)ev.getX();
  containerViewBack.setVisibility(View.VISIBLE);
  beginTrackingSent=false;
  BaseFragment lastFragment=fragmentsStack.get(fragmentsStack.size() - 2);
  View fragmentView=lastFragment.fragmentView;
  if (fragmentView == null) {
    fragmentView=lastFragment.createView(parentActivity);
  }
  ViewGroup parent=(ViewGroup)fragmentView.getParent();
  if (parent != null) {
    lastFragment.onRemoveFromParent();
    parent.removeView(fragmentView);
  }
  if (lastFragment.actionBar != null && lastFragment.actionBar.getAddToContainer()) {
    parent=(ViewGroup)lastFragment.actionBar.getParent();
    if (parent != null) {
      parent.removeView(lastFragment.actionBar);
    }
    if (removeActionBarExtraHeight) {
      lastFragment.actionBar.setOccupyStatusBar(false);
    }
    containerViewBack.addView(lastFragment.actionBar);
    lastFragment.actionBar.setTitleOverlayText(titleOverlayText,titleOverlayTextId,overlayAction);
  }
  containerViewBack.addView(fragmentView);
  LinearLayout.LayoutParams layoutParams=(LinearLayout.LayoutParams)fragmentView.getLayoutParams();
  layoutParams.width=LayoutHelper.MATCH_PARENT;
  layoutParams.height=LayoutHelper.MATCH_PARENT;
  layoutParams.topMargin=layoutParams.bottomMargin=layoutParams.rightMargin=layoutParams.leftMargin=0;
  fragmentView.setLayoutParams(layoutParams);
  if (!lastFragment.hasOwnBackground && fragmentView.getBackground() == null) {
    fragmentView.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite));
  }
  lastFragment.onResume();
}
