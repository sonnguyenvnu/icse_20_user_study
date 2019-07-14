@Override public Thread createThread(){
  return new AnimationThread(){
    @Override public void callDraw(){
      sketch.handleDraw();
      render();
    }
  }
;
}
