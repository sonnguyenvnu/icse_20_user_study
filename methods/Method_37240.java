/** 
 * Loads property descriptor, if property was updated.
 */
private void loadPropertyDescriptor(){
  if (updateProperty) {
    if (cd == null) {
      propertyDescriptor=null;
    }
 else {
      propertyDescriptor=cd.getPropertyDescriptor(name,true);
    }
    updateProperty=false;
  }
}
