private void updateUi(){
  titleChanger.change(currentMonth);
  enableView(buttonPast,canGoBack());
  enableView(buttonFuture,canGoForward());
}
