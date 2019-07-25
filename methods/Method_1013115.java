/** 
 * Moves entries in the table
 * @param moveUp
 */
void move(boolean moveUp){
  final ISelection selection=fTableViewer.getSelection();
  if (selection instanceof IStructuredSelection) {
    final IStructuredSelection ss=(IStructuredSelection)selection;
    final String selected=(String)ss.getFirstElement();
    int index=fLocationList.indexOf(selected);
    if (moveUp && index - 1 >= 0) {
      fLocationList.remove(index);
      fLocationList.add(index - 1,selected);
      fTableViewer.refresh();
      updateEnablementMoveButtons(ss);
    }
 else     if (!moveUp && index + 1 < fLocationList.size()) {
      fLocationList.remove(index);
      fLocationList.add(index + 1,selected);
      fTableViewer.refresh();
      updateEnablementMoveButtons(ss);
    }
  }
}
