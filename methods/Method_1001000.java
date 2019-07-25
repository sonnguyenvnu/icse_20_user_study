protected void _serialize(JsonGenerator gen) throws IOException {
  if (_value instanceof SerializableString) {
    gen.writeRawValue((SerializableString)_value);
  }
 else {
    gen.writeRawValue(String.valueOf(_value));
  }
}
