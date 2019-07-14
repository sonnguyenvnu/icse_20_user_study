private void init(){
  getStyleClass().add(DEFAULT_STYLE_CLASS);
  eventHandlerManager.addEventHandler(WindowEvent.WINDOW_SHOWING,event -> {
    root=getScene().getRoot();
    root.setOpacity(0);
    root.setScaleY(0.8);
    root.setScaleX(0.8);
    animation.setOnFinished(null);
  }
);
  eventHandlerManager.addEventHandler(WindowEvent.WINDOW_SHOWN,event -> {
    setAnchorX(getUpdatedAnchorX());
    setAnchorY(getUpdatedAnchorY());
    animation.reverseAndContinue();
  }
);
}
