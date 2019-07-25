/** 
 * Method called to create  {@link BeanSerializer} instance withall accumulated information. Will construct a serializer if we have enough information, or return null if not.
 */
public JsonSerializer<?> build(){
  BeanPropertyWriter[] properties;
  if (_properties == null || _properties.isEmpty()) {
    if (_anyGetter == null && _objectIdWriter == null) {
      return null;
    }
    properties=NO_PROPERTIES;
  }
 else {
    properties=_properties.toArray(NO_PROPERTIES);
    if (_config.isEnabled(MapperFeature.CAN_OVERRIDE_ACCESS_MODIFIERS)) {
      for (int i=0, end=properties.length; i < end; ++i) {
        properties[i].fixAccess(_config);
      }
    }
  }
  if (_filteredProperties != null) {
    if (_filteredProperties.length != _properties.size()) {
      throw new IllegalStateException(String.format("Mismatch between `properties` size (%d), `filteredProperties` (%s): should have as many (or `null` for latter)",_properties.size(),_filteredProperties.length));
    }
  }
  if (_anyGetter != null) {
    _anyGetter.fixAccess(_config);
  }
  if (_typeId != null) {
    if (_config.isEnabled(MapperFeature.CAN_OVERRIDE_ACCESS_MODIFIERS)) {
      _typeId.fixAccess(_config.isEnabled(MapperFeature.OVERRIDE_PUBLIC_ACCESS_MODIFIERS));
    }
  }
  JsonSerializer<?> ser=UnrolledBeanSerializer.tryConstruct(_beanDesc.getType(),this,properties,_filteredProperties);
  if (ser != null) {
    return ser;
  }
  return new BeanSerializer(_beanDesc.getType(),this,properties,_filteredProperties);
}
