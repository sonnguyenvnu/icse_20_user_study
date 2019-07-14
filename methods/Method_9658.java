@Override public void onSignalBarsCountChanged(final int count){
  runOnUiThread(new Runnable(){
    @Override public void run(){
      signalBarsCount=count;
      brandingText.invalidate();
    }
  }
);
}
