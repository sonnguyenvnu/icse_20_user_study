/** 
 * When down arrow key is pressed, moves the highlighted selection down in the list
 */
protected void moveDown(){
  if (completionList.getSelectedIndex() == completionList.getModel().getSize() - 1) {
    scrollPane.getVerticalScrollBar().setValue(0);
    selectIndex(0);
  }
 else {
    int index=Math.min(completionList.getSelectedIndex() + 1,completionList.getModel().getSize() - 1);
    selectIndex(index);
    int step=scrollPane.getVerticalScrollBar().getMaximum() / completionList.getModel().getSize();
    scrollPane.getVerticalScrollBar().setValue(scrollPane.getVerticalScrollBar().getValue() + step);
  }
}
