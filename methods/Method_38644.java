/** 
 * Reads property using property descriptor.
 */
private Object readProperty(final Object source,final PropertyDescriptor propertyDescriptor){
  Getter getter=propertyDescriptor.getGetter(declared);
  if (getter != null) {
    try {
      return getter.invokeGetter(source);
    }
 catch (    Exception ex) {
      throw new JsonException(ex);
    }
  }
  return null;
}
