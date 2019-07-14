/** 
 * Invokes setter, but first converts type to match the setter type.
 */
protected Object invokeSetter(final Setter setter,final BeanProperty bp,Object value){
  try {
    final MapperFunction setterMapperFunction=setter.getMapperFunction();
    if (setterMapperFunction != null) {
      value=setterMapperFunction.apply(value);
    }
    final Class type=setter.getSetterRawType();
    if (ClassUtil.isTypeOf(type,Collection.class)) {
      Class componentType=setter.getSetterRawComponentType();
      value=convertToCollection(value,type,componentType);
    }
 else {
      value=convertType(value,type);
    }
    setter.invokeSetter(bp.bean,value);
  }
 catch (  Exception ex) {
    if (isSilent) {
      return null;
    }
    throw new BeanException("Setter failed: " + setter,ex);
  }
  return value;
}
