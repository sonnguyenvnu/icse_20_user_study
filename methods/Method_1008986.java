/** 
 * <p>Removes all properties from the section including 0 (dictionary) and 1 (codepage).</p>
 */
public void clear(){
  final Property[] properties=getProperties();
  for (int i=0; i < properties.length; i++) {
    final Property p=properties[i];
    removeProperty(p.getID());
  }
}
