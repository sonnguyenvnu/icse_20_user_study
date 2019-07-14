/** 
 * Creates new instance for current property name through its setter. It uses default constructor!
 */
protected Object createBeanProperty(final BeanProperty bp){
  Setter setter=bp.getSetter(true);
  if (setter == null) {
    return null;
  }
  Class type=setter.getSetterRawType();
  Object newInstance;
  try {
    newInstance=ClassUtil.newInstance(type);
  }
 catch (  Exception ex) {
    if (isSilent) {
      return null;
    }
    throw new BeanException("Invalid property: " + bp.name,bp,ex);
  }
  newInstance=invokeSetter(setter,bp,newInstance);
  return newInstance;
}
