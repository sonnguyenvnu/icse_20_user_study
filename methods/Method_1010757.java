public void repaint(@NotNull jetbrains.mps.openapi.editor.cells.EditorCell cell){
  repaint(0,cell.getY(),getWidth(),cell.getHeight() + 1);
}
