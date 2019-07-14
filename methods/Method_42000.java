private void hideControls(){
  runOnUiThread(() -> {
    getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_IMMERSIVE);
    toolbar.animate().translationY(-toolbar.getHeight()).setInterpolator(new AccelerateInterpolator()).setDuration(200).start();
    fullScreenMode=true;
    changeBackGroundColor();
  }
);
}
