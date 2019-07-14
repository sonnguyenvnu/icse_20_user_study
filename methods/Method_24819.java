protected void updateTweakInterface(List<List<Handle>> handles,List<List<ColorControlBox>> colorBoxes){
  this.handles=handles;
  this.colorBoxes=colorBoxes;
  updateTweakInterfacePositions();
  repaint();
}
