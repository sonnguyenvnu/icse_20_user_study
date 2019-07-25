@SuppressWarnings("resource") protected final Object _deserialize(JsonParser p,DeserializationContext ctxt,int index,String typeId) throws IOException {
  JsonParser p2=_tokens[index].asParser(ctxt,p);
  JsonToken t=p2.nextToken();
  if (t == JsonToken.VALUE_NULL) {
    return null;
  }
  TokenBuffer merged=new TokenBuffer(p,ctxt);
  merged.writeStartArray();
  merged.writeString(typeId);
  merged.copyCurrentStructure(p2);
  merged.writeEndArray();
  JsonParser mp=merged.asParser(ctxt,p);
  mp.nextToken();
  return _properties[index].getProperty().deserialize(mp,ctxt);
}
