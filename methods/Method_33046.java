private void handleDragEventOnDecoratorPane(MouseEvent mouseEvent){
  isDragging=true;
  if (!mouseEvent.isPrimaryButtonDown() || (xOffset == -1 && yOffset == -1)) {
    return;
  }
  if (primaryStage.isFullScreen() || mouseEvent.isStillSincePress() || primaryStage.isMaximized() || maximized) {
    return;
  }
  newX=mouseEvent.getScreenX();
  newY=mouseEvent.getScreenY();
  double deltax=newX - initX;
  double deltay=newY - initY;
  Cursor cursor=this.getCursor();
  if (Cursor.E_RESIZE.equals(cursor)) {
    setStageWidth(initWidth + deltax);
    mouseEvent.consume();
  }
 else   if (Cursor.NE_RESIZE.equals(cursor)) {
    if (setStageHeight(initHeight - deltay)) {
      primaryStage.setY(initStageY + deltay);
    }
    setStageWidth(initWidth + deltax);
    mouseEvent.consume();
  }
 else   if (Cursor.SE_RESIZE.equals(cursor)) {
    setStageWidth(initWidth + deltax);
    setStageHeight(initHeight + deltay);
    mouseEvent.consume();
  }
 else   if (Cursor.S_RESIZE.equals(cursor)) {
    setStageHeight(initHeight + deltay);
    mouseEvent.consume();
  }
 else   if (Cursor.W_RESIZE.equals(cursor)) {
    if (setStageWidth(initWidth - deltax)) {
      primaryStage.setX(initStageX + deltax);
    }
    mouseEvent.consume();
  }
 else   if (Cursor.SW_RESIZE.equals(cursor)) {
    if (setStageWidth(initWidth - deltax)) {
      primaryStage.setX(initStageX + deltax);
    }
    setStageHeight(initHeight + deltay);
    mouseEvent.consume();
  }
 else   if (Cursor.NW_RESIZE.equals(cursor)) {
    if (setStageWidth(initWidth - deltax)) {
      primaryStage.setX(initStageX + deltax);
    }
    if (setStageHeight(initHeight - deltay)) {
      primaryStage.setY(initStageY + deltay);
    }
    mouseEvent.consume();
  }
 else   if (Cursor.N_RESIZE.equals(cursor)) {
    if (setStageHeight(initHeight - deltay)) {
      primaryStage.setY(initStageY + deltay);
    }
    mouseEvent.consume();
  }
 else   if (allowMove) {
    primaryStage.setX(mouseEvent.getScreenX() - xOffset);
    primaryStage.setY(mouseEvent.getScreenY() - yOffset);
    mouseEvent.consume();
  }
}
