private void fixLayoutInternal(final boolean resume){
  if (listView != null) {
    int height=(actionBar.getOccupyStatusBar() ? AndroidUtilities.statusBarHeight : 0) + ActionBar.getCurrentActionBarHeight();
    int viewHeight=fragmentView.getMeasuredHeight();
    if (viewHeight == 0) {
      return;
    }
    overScrollHeight=viewHeight - AndroidUtilities.dp(66) - height;
    FrameLayout.LayoutParams layoutParams=(FrameLayout.LayoutParams)listView.getLayoutParams();
    layoutParams.topMargin=height;
    listView.setLayoutParams(layoutParams);
    layoutParams=(FrameLayout.LayoutParams)mapViewClip.getLayoutParams();
    layoutParams.topMargin=height;
    layoutParams.height=overScrollHeight;
    mapViewClip.setLayoutParams(layoutParams);
    if (searchListView != null) {
      layoutParams=(FrameLayout.LayoutParams)searchListView.getLayoutParams();
      layoutParams.topMargin=height;
      searchListView.setLayoutParams(layoutParams);
    }
    adapter.setOverScrollHeight(overScrollHeight);
    layoutParams=(FrameLayout.LayoutParams)mapView.getLayoutParams();
    if (layoutParams != null) {
      layoutParams.height=overScrollHeight + AndroidUtilities.dp(10);
      if (googleMap != null) {
        googleMap.setPadding(AndroidUtilities.dp(70),0,AndroidUtilities.dp(70),AndroidUtilities.dp(10));
      }
      mapView.setLayoutParams(layoutParams);
    }
    adapter.notifyDataSetChanged();
    if (resume) {
      layoutManager.scrollToPositionWithOffset(0,-AndroidUtilities.dp(32 + (liveLocationType == 1 || liveLocationType == 2 ? 66 : 0)));
      updateClipView(layoutManager.findFirstVisibleItemPosition());
      listView.post(() -> {
        layoutManager.scrollToPositionWithOffset(0,-AndroidUtilities.dp(32 + (liveLocationType == 1 || liveLocationType == 2 ? 66 : 0)));
        updateClipView(layoutManager.findFirstVisibleItemPosition());
      }
);
    }
 else {
      updateClipView(layoutManager.findFirstVisibleItemPosition());
    }
  }
}
