private void paintPath(final Path path){
  path.setup(renderView.getCurrentColor(),renderView.getCurrentWeight(),renderView.getCurrentBrush());
  if (clearBuffer) {
    lastRemainder=0.0f;
  }
  path.remainder=lastRemainder;
  renderView.getPainting().paintStroke(path,clearBuffer,new Runnable(){
    @Override public void run(){
      AndroidUtilities.runOnUIThread(new Runnable(){
        @Override public void run(){
          lastRemainder=path.remainder;
          clearBuffer=false;
        }
      }
);
    }
  }
);
}
