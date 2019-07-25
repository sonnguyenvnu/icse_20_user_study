@SuppressWarnings("unchecked") public void resolve(SerializerProvider provider) throws JsonMappingException {
  JsonSerializer<?> ser=provider.handlePrimaryContextualization(_serializer,_property);
  _serializer=(JsonSerializer<Object>)ser;
  if (ser instanceof MapSerializer) {
    _mapSerializer=(MapSerializer)ser;
  }
}
