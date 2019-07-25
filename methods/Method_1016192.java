/** 
 * Repaints list cell for the specified value.
 * @param value cell value
 */
public void repaint(final Object value){
  final ListModel model=getModel();
  if (model instanceof WebListModel) {
    repaint(((WebListModel)model).indexOf(value));
  }
 else {
    for (int i=0; i < model.getSize(); i++) {
      if (model.getElementAt(i) == value) {
        repaint(i);
        break;
      }
    }
  }
}
