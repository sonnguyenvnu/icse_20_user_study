@Override public void resize(RectF screen,RectF virtualScreen){
  this.screen=screen;
  this.virtualScreen=virtualScreen;
  int width=Math.round(screen.width());
  int height=Math.round(screen.height());
  snapRadius=keyScales[0];
  for (int i=1; i < keyScales.length; i++) {
    if (keyScales[i] < snapRadius) {
      snapRadius=keyScales[i];
    }
  }
  keySize=(float)Math.max(width,height) / 12;
  snapRadius=keySize * snapRadius / 4;
  for (int group=0; group < keyScaleGroups.length; group++) {
    resizeKeyGroup(group);
  }
  snapKeys();
  repaint();
}
