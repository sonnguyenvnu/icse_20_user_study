private void showSystemUI(){
  runOnUiThread(new Runnable(){
    public void run(){
      toolbar.animate().translationY(Measure.getStatusBarHeight(getResources())).setInterpolator(new DecelerateInterpolator()).setDuration(240).start();
      getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
      fullScreenMode=false;
      changeBackGroundColor();
    }
  }
);
}
