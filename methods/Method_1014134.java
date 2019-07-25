/** 
 * Disposes this instance and releases all resources.
 */
public void dispose(){
  for (  Item item : items) {
    if (item instanceof GenericItem) {
      ((GenericItem)item).removeStateChangeListener(this);
    }
 else     if (item instanceof GroupItem) {
      ((GroupItem)item).removeStateChangeListener(this);
    }
  }
}
