/** 
 * Extracts type of current property.
 */
protected Class extractType(final BeanProperty bp){
  Getter getter=bp.getGetter(isDeclared);
  if (getter != null) {
    if (bp.index != null) {
      Class type=getter.getGetterRawComponentType();
      return type == null ? Object.class : type;
    }
    return getter.getGetterRawType();
  }
  return null;
}
