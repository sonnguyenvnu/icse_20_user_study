public Object readValue(final String name){
  String propertyName=name;
  if (type != null) {
    final int dotNdx=propertyName.indexOf('.');
    if (dotNdx == -1) {
      return value;
    }
    propertyName=propertyName.substring(dotNdx + 1);
  }
  return BeanUtil.declared.getProperty(value,propertyName);
}
