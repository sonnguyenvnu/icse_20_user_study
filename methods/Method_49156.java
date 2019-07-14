public final Object verifyAttribute(PropertyKey key,Object attribute){
  if (attribute == null)   throw new SchemaViolationException("Property value cannot be null");
  Class<?> datatype=key.dataType();
  if (datatype.equals(Object.class)) {
    if (!attributeHandler.validDataType(attribute.getClass())) {
      throw Property.Exceptions.dataTypeOfPropertyValueNotSupported(attribute);
    }
    return attribute;
  }
 else {
    if (!attribute.getClass().equals(datatype)) {
      Object converted=null;
      try {
        converted=attributeHandler.convert(datatype,attribute);
      }
 catch (      IllegalArgumentException e) {
      }
      if (converted == null)       throw new SchemaViolationException("Value [%s] is not an instance of the expected data type for property key [%s] and cannot be converted. Expected: %s, found: %s",attribute,key.name(),datatype,attribute.getClass());
      attribute=converted;
    }
    assert (attribute.getClass().equals(datatype));
    attributeHandler.verifyAttribute(datatype,attribute);
    return attribute;
  }
}
