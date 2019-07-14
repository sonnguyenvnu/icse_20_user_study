private void updateInitMouseValues(MouseEvent mouseEvent){
  initStageX=primaryStage.getX();
  initStageY=primaryStage.getY();
  initWidth=primaryStage.getWidth();
  initHeight=primaryStage.getHeight();
  initX=mouseEvent.getScreenX();
  initY=mouseEvent.getScreenY();
  xOffset=mouseEvent.getSceneX();
  yOffset=mouseEvent.getSceneY();
}
