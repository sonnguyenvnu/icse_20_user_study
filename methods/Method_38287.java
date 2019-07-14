/** 
 * Returns <code>true</code> if a value is considered empty i.e. not existing.
 */
protected boolean isEmptyColumnValue(final DbEntityColumnDescriptor dec,final Object value){
  if (value == null) {
    return true;
  }
  if (dec.isId() && value instanceof Number) {
    final double d=((Number)value).doubleValue();
    if (d == 0.0d) {
      return true;
    }
  }
  if (dec.getPropertyType().isPrimitive()) {
    if (char.class == dec.getPropertyType()) {
      final Character c=((Character)value);
      if ('\u0000' == c.charValue()) {
        return true;
      }
    }
 else {
      final double d=((Number)value).doubleValue();
      if (d == 0) {
        return true;
      }
    }
  }
  if (value instanceof CharSequence) {
    if (StringUtil.isBlank((CharSequence)value)) {
      return true;
    }
  }
  return false;
}
