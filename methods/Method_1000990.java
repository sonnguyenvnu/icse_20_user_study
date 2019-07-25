@Override public void serialize(JsonGenerator gen,SerializerProvider provider) throws IOException, JsonProcessingException {
  gen.writeString(toCanonical());
}
