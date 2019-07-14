public void setSource(@NonNull String source,boolean wrap){
  if (!InputHelper.isEmpty(source)) {
    WebSettings settings=getSettings();
    settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.TEXT_AUTOSIZING);
    setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
    settings.setSupportZoom(!wrap);
    settings.setBuiltInZoomControls(!wrap);
    if (!wrap)     settings.setDisplayZoomControls(false);
    String page=PrettifyHelper.generateContent(source,AppHelper.isNightMode(getResources()),wrap);
    loadCode(page);
  }
}
