private void resetCursor(){
  SwingUtilities.invokeLater(new Runnable(){
    @Override public void run(){
      textArea.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }
  }
);
}
