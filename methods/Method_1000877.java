@Override public Collection<String> deserialize(JsonParser p,DeserializationContext ctxt,Collection<String> result) throws IOException {
  if (!p.isExpectedStartArrayToken()) {
    return handleNonArray(p,ctxt,result);
  }
  if (_valueDeserializer != null) {
    return deserializeUsingCustom(p,ctxt,result,_valueDeserializer);
  }
  try {
    while (true) {
      String value=p.nextTextValue();
      if (value != null) {
        result.add(value);
        continue;
      }
      JsonToken t=p.currentToken();
      if (t == JsonToken.END_ARRAY) {
        break;
      }
      if (t == JsonToken.VALUE_NULL) {
        if (_skipNullValues) {
          continue;
        }
        value=(String)_nullProvider.getNullValue(ctxt);
      }
 else {
        value=_parseString(p,ctxt);
      }
      result.add(value);
    }
  }
 catch (  Exception e) {
    throw JsonMappingException.wrapWithPath(e,result,result.size());
  }
  return result;
}
