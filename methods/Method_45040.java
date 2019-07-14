private void updateOpenClass(final OpenFile open){
  if (open.getType() == null) {
    return;
  }
  new Thread(new Runnable(){
    @Override public void run(){
      try {
        bar.setVisible(true);
        getLabel().setText("Extracting: " + open.name);
        open.invalidateContent();
        open.decompile();
        getLabel().setText("Complete");
      }
 catch (      Exception e) {
        getLabel().setText("Error, cannot update: " + open.name);
      }
 finally {
        bar.setVisible(false);
      }
    }
  }
).start();
}
