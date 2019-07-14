/** 
 * ????????????????????? //	 * @param LexemePath path
 * @return
 */
private Stack<QuickSortSet.Cell> forwardPath(QuickSortSet.Cell lexemeCell,LexemePath option){
  Stack<QuickSortSet.Cell> conflictStack=new Stack<QuickSortSet.Cell>();
  QuickSortSet.Cell c=lexemeCell;
  while (c != null && c.getLexeme() != null) {
    if (!option.addNotCrossLexeme(c.getLexeme())) {
      conflictStack.push(c);
    }
    c=c.getNext();
  }
  return conflictStack;
}
