public void serialize(JsonGenerator gen) throws IOException {
  if (_value instanceof JsonSerializable) {
    gen.writeObject(_value);
  }
 else {
    _serialize(gen);
  }
}
