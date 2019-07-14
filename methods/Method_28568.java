public void loadImage(@NonNull String url,boolean isSvg){
  WebSettings settings=getSettings();
  settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
  setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
  settings.setSupportZoom(true);
  settings.setBuiltInZoomControls(true);
  settings.setDisplayZoomControls(false);
  String html;
  if (isSvg) {
    html=url;
  }
 else {
    html="<html><head><style>img{display: inline; height: auto; max-width: 100%;}</style></head><body>" + "<img src=\"" + url + "\"/></body></html>";
  }
  Logger.e(html);
  loadData(html,"text/html",null);
}
