@Override public void navigate(boolean requestFocus){
  for (  FileEditor fe : FileEditorManager.getInstance(myProject).openFile(myVirtualFile,true,true)) {
    if (!(fe instanceof TextEditor)) {
      continue;
    }
    TextEditor te=(TextEditor)fe;
    Document document=te.getEditor().getDocument();
    if (myOffset != -1) {
      te.getEditor().getCaretModel().moveToOffset(Math.min(myOffset,document.getTextLength()));
    }
 else {
      int maxLines=document.getLineCount();
      int line=Math.min(Math.max(0,myLine),maxLines - 1);
      int lineWidth=document.getLineEndOffset(line) - document.getLineStartOffset(line);
      int column=Math.min(Math.max(0,myColumn),lineWidth);
      LogicalPosition position=new LogicalPosition(line,column);
      te.getEditor().getCaretModel().moveToLogicalPosition(position);
    }
    te.getEditor().getScrollingModel().scrollToCaret(ScrollType.MAKE_VISIBLE);
    return;
  }
}
