private void updateDrawerPosition(JFXDrawer drawer){
  int index=drawers.indexOf(drawer);
  if (index + 1 < drawers.size()) {
    if (index - 1 >= 0) {
      drawers.get(index + 1).setContent(drawers.get(index - 1));
    }
 else     if (index == 0) {
      drawers.get(index + 1).setContent(content);
    }
    drawer.setContent(drawers.get(drawers.size() - 1));
    drawers.remove(drawer);
    drawers.add(drawer);
    getChildren().setAll(drawer);
  }
}
