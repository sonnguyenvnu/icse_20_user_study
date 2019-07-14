private void updateClipView(int firstVisibleItem){
  if (firstVisibleItem == RecyclerView.NO_POSITION) {
    return;
  }
  int height=0;
  int top=0;
  View child=listView.getChildAt(0);
  if (child != null) {
    if (firstVisibleItem == 0) {
      top=child.getTop();
      height=overScrollHeight + (top < 0 ? top : 0);
    }
    FrameLayout.LayoutParams layoutParams=(FrameLayout.LayoutParams)mapViewClip.getLayoutParams();
    if (layoutParams != null) {
      if (height <= 0) {
        if (mapView.getVisibility() == View.VISIBLE) {
          mapView.setVisibility(View.INVISIBLE);
          mapViewClip.setVisibility(View.INVISIBLE);
        }
      }
 else {
        if (mapView.getVisibility() == View.INVISIBLE) {
          mapView.setVisibility(View.VISIBLE);
          mapViewClip.setVisibility(View.VISIBLE);
        }
      }
      mapViewClip.setTranslationY(Math.min(0,top));
      mapView.setTranslationY(Math.max(0,-top / 2));
      if (markerImageView != null) {
        markerImageView.setTranslationY(markerTop=-top - AndroidUtilities.dp(42) + height / 2);
        markerXImageView.setTranslationY(-top - AndroidUtilities.dp(7) + height / 2);
      }
      if (routeButton != null) {
        routeButton.setTranslationY(top);
      }
      layoutParams=(FrameLayout.LayoutParams)mapView.getLayoutParams();
      if (layoutParams != null && layoutParams.height != overScrollHeight + AndroidUtilities.dp(10)) {
        layoutParams.height=overScrollHeight + AndroidUtilities.dp(10);
        if (googleMap != null) {
          googleMap.setPadding(AndroidUtilities.dp(70),0,AndroidUtilities.dp(70),AndroidUtilities.dp(10));
        }
        mapView.setLayoutParams(layoutParams);
      }
    }
  }
}
