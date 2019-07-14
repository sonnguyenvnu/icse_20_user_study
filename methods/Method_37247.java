/** 
 * Sets a value of simple property.
 */
@SuppressWarnings({"unchecked"}) protected void setSimpleProperty(final BeanProperty bp,final Object value){
  Setter setter=bp.getSetter(isDeclared);
  if (setter != null) {
    invokeSetter(setter,bp,value);
    return;
  }
  if (bp.isMap()) {
    ((Map)bp.bean).put(bp.name,value);
    return;
  }
  if (isSilent) {
    return;
  }
  throw new BeanException("Simple property not found: " + bp.name,bp);
}
