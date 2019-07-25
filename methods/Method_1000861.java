/** 
 * Specialized method that can be used to replace an existing entry (note: entry MUST exist; otherwise exception is thrown) with specified replacement.
 */
public void replace(SettableBeanProperty oldProp,SettableBeanProperty newProp){
  for (int i=0, end=_propsInOrder.length; i < end; ++i) {
    if (_propsInOrder[i] == oldProp) {
      _propsInOrder[i]=newProp;
      return;
    }
  }
  throw new NoSuchElementException("No entry '" + oldProp.getName() + "' found, can't replace");
}
