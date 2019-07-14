/** 
 * Returns the closest matching converter for the given type, but not very efficiently.
 */
private static Converter selectSlow(ConverterSet set,Class<?> type){
  Converter[] converters=set.iConverters;
  int length=converters.length;
  Converter converter;
  for (int i=length; --i >= 0; ) {
    converter=converters[i];
    Class<?> supportedType=converter.getSupportedType();
    if (supportedType == type) {
      return converter;
    }
    if (supportedType == null || (type != null && !supportedType.isAssignableFrom(type))) {
      set=set.remove(i,null);
      converters=set.iConverters;
      length=converters.length;
    }
  }
  if (type == null || length == 0) {
    return null;
  }
  if (length == 1) {
    return converters[0];
  }
  for (int i=length; --i >= 0; ) {
    converter=converters[i];
    Class<?> supportedType=converter.getSupportedType();
    for (int j=length; --j >= 0; ) {
      if (j != i && converters[j].getSupportedType().isAssignableFrom(supportedType)) {
        set=set.remove(j,null);
        converters=set.iConverters;
        length=converters.length;
        i=length - 1;
      }
    }
  }
  if (length == 1) {
    return converters[0];
  }
  StringBuilder msg=new StringBuilder();
  msg.append("Unable to find best converter for type \"");
  msg.append(type.getName());
  msg.append("\" from remaining set: ");
  for (int i=0; i < length; i++) {
    converter=converters[i];
    Class<?> supportedType=converter.getSupportedType();
    msg.append(converter.getClass().getName());
    msg.append('[');
    msg.append(supportedType == null ? null : supportedType.getName());
    msg.append("], ");
  }
  throw new IllegalStateException(msg.toString());
}
