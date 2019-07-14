/** 
 * When up arrow key is pressed, moves the highlighted selection up in the list
 */
protected void moveUp(){
  if (completionList.getSelectedIndex() == 0) {
    scrollPane.getVerticalScrollBar().setValue(scrollPane.getVerticalScrollBar().getMaximum());
    selectIndex(completionList.getModel().getSize() - 1);
  }
 else {
    int index=Math.max(completionList.getSelectedIndex() - 1,0);
    selectIndex(index);
    int step=scrollPane.getVerticalScrollBar().getMaximum() / completionList.getModel().getSize();
    scrollPane.getVerticalScrollBar().setValue(scrollPane.getVerticalScrollBar().getValue() - step);
  }
}
