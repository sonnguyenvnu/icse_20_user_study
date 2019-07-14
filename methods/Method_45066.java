private void scrollToSelection(final int selectionBeginningOffset){
  SwingUtilities.invokeLater(new Runnable(){
    @Override public void run(){
      try {
        int fullHeight=textArea.getBounds().height;
        int viewportHeight=textArea.getVisibleRect().height;
        int viewportLineCount=viewportHeight / textArea.getLineHeight();
        int selectionLineNum=textArea.getLineOfOffset(selectionBeginningOffset);
        int upperMarginToScroll=Math.round(viewportLineCount * 0.29f);
        int upperLineToSet=selectionLineNum - upperMarginToScroll;
        int currentUpperLine=textArea.getVisibleRect().y / textArea.getLineHeight();
        if (selectionLineNum <= currentUpperLine + 2 || selectionLineNum >= currentUpperLine + viewportLineCount - 4) {
          Rectangle rectToScroll=new Rectangle();
          rectToScroll.x=0;
          rectToScroll.width=1;
          rectToScroll.y=Math.max(upperLineToSet * textArea.getLineHeight(),0);
          rectToScroll.height=Math.min(viewportHeight,fullHeight - rectToScroll.y);
          textArea.scrollRectToVisible(rectToScroll);
        }
      }
 catch (      Exception e) {
        Luyten.showExceptionDialog("Exception!",e);
      }
    }
  }
);
}
