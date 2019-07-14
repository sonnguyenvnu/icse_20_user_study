/** 
 * @see org.fife.ui.rsyntaxtextarea.RSyntaxUtilities#selectAndPossiblyCenter Force center and do not select
 */
public void setCaretPositionAndCenter(DocumentRange range){
  final int start=range.getStartOffset();
  final int end=range.getEndOffset();
  boolean foldsExpanded=false;
  FoldManager fm=textArea.getFoldManager();
  if (fm.isCodeFoldingSupportedAndEnabled()) {
    foldsExpanded=fm.ensureOffsetNotInClosedFold(start);
    foldsExpanded|=fm.ensureOffsetNotInClosedFold(end);
  }
  if (!foldsExpanded) {
    try {
      Rectangle rec=textArea.modelToView(start);
      if (rec != null) {
        setCaretPositionAndCenter(start,end,rec);
      }
 else {
        SwingUtilities.invokeLater(() -> {
          try {
            Rectangle r=textArea.modelToView(start);
            if (r != null) {
              setCaretPositionAndCenter(start,end,r);
            }
          }
 catch (          BadLocationException e) {
            assert ExceptionUtil.printStackTrace(e);
          }
        }
);
      }
    }
 catch (    BadLocationException e) {
      assert ExceptionUtil.printStackTrace(e);
    }
  }
}
