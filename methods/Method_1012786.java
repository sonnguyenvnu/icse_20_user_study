private void update(){
  SwingUtilities.invokeLater(new Runnable(){
    @Override public void run(){
      updateOnGuiThread();
    }
  }
);
}
