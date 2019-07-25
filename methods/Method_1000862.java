/** 
 * Specialized method for removing specified existing entry. NOTE: entry MUST exist, otherwise an exception is thrown.
 */
public void remove(SettableBeanProperty propToRm){
  final String key=propToRm.getName();
  ArrayList<SettableBeanProperty> props=new ArrayList<SettableBeanProperty>(_propsInOrder.length);
  boolean found=false;
  for (  SettableBeanProperty prop : _propsInOrder) {
    if (!found) {
      String match=prop.getName();
      if (found=match.equals(key)) {
        continue;
      }
    }
    props.add(prop);
  }
  if (!found) {
    throw new NoSuchElementException("No entry '" + propToRm.getName() + "' found, can't remove");
  }
  _propsInOrder=props.toArray(new SettableBeanProperty[0]);
}
