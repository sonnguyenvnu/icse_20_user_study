private void applyCameraViewPosition(){
  if (cameraView != null) {
    if (!cameraOpened) {
      cameraView.setTranslationX(cameraViewLocation[0]);
      cameraView.setTranslationY(cameraViewLocation[1]);
    }
    cameraIcon.setTranslationX(cameraViewLocation[0]);
    cameraIcon.setTranslationY(cameraViewLocation[1]);
    int finalWidth=AndroidUtilities.dp(80) - cameraViewOffsetX;
    int finalHeight=AndroidUtilities.dp(80) - cameraViewOffsetY;
    FrameLayout.LayoutParams layoutParams;
    if (!cameraOpened) {
      cameraView.setClipLeft(cameraViewOffsetX);
      cameraView.setClipTop(cameraViewOffsetY);
      layoutParams=(FrameLayout.LayoutParams)cameraView.getLayoutParams();
      if (layoutParams.height != finalHeight || layoutParams.width != finalWidth) {
        layoutParams.width=finalWidth;
        layoutParams.height=finalHeight;
        cameraView.setLayoutParams(layoutParams);
        final FrameLayout.LayoutParams layoutParamsFinal=layoutParams;
        AndroidUtilities.runOnUIThread(() -> {
          if (cameraView != null) {
            cameraView.setLayoutParams(layoutParamsFinal);
          }
        }
);
      }
    }
    layoutParams=(FrameLayout.LayoutParams)cameraIcon.getLayoutParams();
    if (layoutParams.height != finalHeight || layoutParams.width != finalWidth) {
      layoutParams.width=finalWidth;
      layoutParams.height=finalHeight;
      cameraIcon.setLayoutParams(layoutParams);
      final FrameLayout.LayoutParams layoutParamsFinal=layoutParams;
      AndroidUtilities.runOnUIThread(() -> {
        if (cameraIcon != null) {
          cameraIcon.setLayoutParams(layoutParamsFinal);
        }
      }
);
    }
  }
}
