public void setThemeSource(@NonNull String source,@Nullable String theme){
  if (!InputHelper.isEmpty(source)) {
    WebSettings settings=getSettings();
    settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.TEXT_AUTOSIZING);
    setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
    settings.setSupportZoom(true);
    settings.setBuiltInZoomControls(true);
    settings.setDisplayZoomControls(false);
    String page=PrettifyHelper.generateContent(source,theme);
    loadCode(page);
  }
}
