public void onLegalMenu(){
  new Thread(){
    public void run(){
      try {
        bar.setVisible(true);
        bar.setIndeterminate(true);
        String legalStr=getLegalStr();
        MainWindow.this.getModel().showLegal(legalStr);
      }
  finally {
        bar.setIndeterminate(false);
        bar.setVisible(false);
      }
    }
  }
.start();
}
