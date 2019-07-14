@TargetApi(Build.VERSION_CODES.KITKAT_WATCH) private void init(){
  setFitsSystemWindows(false);
  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT_WATCH) {
    setOnApplyWindowInsetsListener((view,insets) -> {
      if (getHeaderCount() == 0) {
        return FullscreenNavigationView.this.onApplyWindowInsets(insets);
      }
      return getHeaderView(0).onApplyWindowInsets(insets);
    }
);
  }
}
