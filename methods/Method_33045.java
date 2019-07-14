private void showDragCursorOnBorders(MouseEvent mouseEvent){
  if (primaryStage.isMaximized() || primaryStage.isFullScreen() || maximized) {
    this.setCursor(Cursor.DEFAULT);
    return;
  }
  if (!primaryStage.isResizable()) {
    return;
  }
  double x=mouseEvent.getX();
  double y=mouseEvent.getY();
  if (contentPlaceHolder.getBorder() != null && contentPlaceHolder.getBorder().getStrokes().size() > 0) {
    double borderWidth=contentPlaceHolder.snappedLeftInset();
    if (isRightEdge(x)) {
      if (y < borderWidth) {
        this.setCursor(Cursor.NE_RESIZE);
      }
 else       if (y > this.getHeight() - borderWidth) {
        this.setCursor(Cursor.SE_RESIZE);
      }
 else {
        this.setCursor(Cursor.E_RESIZE);
      }
    }
 else     if (isLeftEdge(x)) {
      if (y < borderWidth) {
        this.setCursor(Cursor.NW_RESIZE);
      }
 else       if (y > this.getHeight() - borderWidth) {
        this.setCursor(Cursor.SW_RESIZE);
      }
 else {
        this.setCursor(Cursor.W_RESIZE);
      }
    }
 else     if (isTopEdge(y)) {
      this.setCursor(Cursor.N_RESIZE);
    }
 else     if (isBottomEdge(y)) {
      this.setCursor(Cursor.S_RESIZE);
    }
 else {
      this.setCursor(Cursor.DEFAULT);
    }
  }
}
