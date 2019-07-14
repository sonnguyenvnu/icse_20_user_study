/** 
 * @see ViewerModelListener#viewerModelChanged(ViewerModelEvent)
 */
@Override public void viewerModelChanged(ViewerModelEvent e){
  if (e.getReason() == ViewerModelEvent.NODE_SELECTED) {
    final Node node=(Node)e.getParameter();
    SwingUtilities.invokeLater(new Runnable(){
      @Override public void run(){
        try {
          sourceCodeArea.getHighlighter().removeAllHighlights();
          if (node == null) {
            return;
          }
          int startOffset=sourceCodeArea.getLineStartOffset(node.getBeginLine() - 1) + node.getBeginColumn() - 1;
          int end=sourceCodeArea.getLineStartOffset(node.getEndLine() - 1) + node.getEndColumn();
          sourceCodeArea.getHighlighter().addHighlight(startOffset,end,new DefaultHighlighter.DefaultHighlightPainter(HIGHLIGHT_COLOR));
          sourceCodeArea.moveCaretPosition(startOffset);
        }
 catch (        BadLocationException exc) {
          throw new IllegalStateException(exc.getMessage());
        }
      }
    }
);
  }
}
