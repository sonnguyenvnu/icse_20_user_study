private final void _serialize(JsonGenerator gen,Object value,JsonSerializer<Object> ser,PropertyName rootName) throws IOException {
  try {
    gen.writeStartObject();
    gen.writeFieldName(rootName.simpleAsEncoded(_config));
    ser.serialize(value,gen,this);
    gen.writeEndObject();
  }
 catch (  Exception e) {
    throw _wrapAsIOE(gen,e);
  }
}
