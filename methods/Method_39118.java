public void writeValue(final String name,final Object propertyValue,final boolean silent){
  String propertyName=name;
  if (type != null) {
    int dotNdx=propertyName.indexOf('.');
    if (dotNdx == -1) {
      if (mapperFunction != null) {
        value=mapperFunction.apply(propertyValue);
      }
 else {
        value=TypeConverterManager.get().convertType(propertyValue,type);
      }
      return;
    }
    if (value == null) {
      value=valueInstanceCreator.apply(type);
    }
    propertyName=propertyName.substring(dotNdx + 1);
  }
  if (silent) {
    BeanUtil.declaredForcedSilent.setProperty(value,propertyName,propertyValue);
  }
 else {
    BeanUtil.declaredForced.setProperty(value,propertyName,propertyValue);
  }
}
