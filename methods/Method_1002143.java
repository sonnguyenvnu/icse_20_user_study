public void destroy(){
  if (window instanceof DefaultWindow) {
    ((DefaultWindow)window).removeAllViews();
  }
}
