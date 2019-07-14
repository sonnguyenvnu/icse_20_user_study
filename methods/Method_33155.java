private void show(SnackbarEvent event){
  content.getChildren().setAll(event.getContent());
  openAnimation=getTimeline(event.getTimeout());
  if (event.getPseudoClass() != null) {
    activePseudoClass=event.getPseudoClass();
    content.pseudoClassStateChanged(activePseudoClass,true);
  }
  openAnimation.play();
}
