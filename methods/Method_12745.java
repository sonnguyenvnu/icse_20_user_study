public void setTheme(int resid){
  Reflector.QuietReflector.with(this.mResources).field("mThemeResId").set(resid);
}
