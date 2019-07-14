private void restoreScrollPosition(final double position){
  SwingUtilities.invokeLater(new Runnable(){
    @Override public void run(){
      JScrollBar verticalScrollbar=scrollPane.getVerticalScrollBar();
      if (verticalScrollbar == null)       return;
      int scrollMax=verticalScrollbar.getMaximum() - verticalScrollbar.getMinimum();
      long newScrollValue=Math.round(position * scrollMax) + verticalScrollbar.getMinimum();
      if (newScrollValue < verticalScrollbar.getMinimum())       newScrollValue=verticalScrollbar.getMinimum();
      if (newScrollValue > verticalScrollbar.getMaximum())       newScrollValue=verticalScrollbar.getMaximum();
      verticalScrollbar.setValue((int)newScrollValue);
    }
  }
);
}
