private void setContentPreserveLastScrollPosition(final String content){
  final Double scrollPercent=lastScrollPercent;
  if (scrollPercent != null && initialNavigationLink == null) {
    SwingUtilities.invokeLater(new Runnable(){
      @Override public void run(){
        textArea.setText(content);
        restoreScrollPosition(scrollPercent);
      }
    }
);
  }
 else {
    textArea.setText(content);
  }
}
