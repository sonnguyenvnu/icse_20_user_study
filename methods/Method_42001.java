private void showControls(){
  runOnUiThread(() -> {
    int rotation=(((WindowManager)getSystemService(WINDOW_SERVICE)).getDefaultDisplay()).getRotation();
    if (rotation == Surface.ROTATION_90 || rotation == Surface.ROTATION_270) {
      getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
      simpleExoPlayerView.setPaddingRelative(0,0,0,0);
    }
 else {
      getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
      simpleExoPlayerView.setPaddingRelative(0,0,0,Measure.getNavBarHeight(getApplicationContext()));
    }
    toolbar.animate().translationY(Measure.getStatusBarHeight(getResources())).setInterpolator(new DecelerateInterpolator()).setDuration(240).start();
    fullScreenMode=false;
    changeBackGroundColor();
  }
);
}
