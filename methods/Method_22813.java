protected void updateBracketHighlight(int newCaretPosition){
  if (newCaretPosition == 0) {
    bracketPosition=bracketLine=-1;
    return;
  }
  try {
    int offset=bracketHelper.findMatchingBracket(document.getText(0,document.getLength()),newCaretPosition - 1);
    if (offset != -1) {
      bracketLine=getLineOfOffset(offset);
      bracketPosition=offset - getLineStartOffset(bracketLine);
      return;
    }
  }
 catch (  BadLocationException bl) {
    bl.printStackTrace();
  }
  bracketLine=bracketPosition=-1;
}
