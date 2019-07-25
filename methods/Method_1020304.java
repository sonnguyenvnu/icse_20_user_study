@Override public void resize(RectF screen,RectF virtualScreen){
  this.screen=screen;
  this.virtualScreen=virtualScreen;
  float keyWidth=screen.width() / KEY_WIDTH_RATIO;
  float keyHeight=keyWidth / KEY_HEIGHT_RATIO;
  for (int i=0; i < keypad.length; i++) {
    keypad[i].resize(keyWidth,keyHeight);
    snapValid[i]=false;
  }
  snapKeys();
  repaint();
}
