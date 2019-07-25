/** 
 * Actual implementation of value reading+binding operation.
 */
protected Object _bind(DefaultDeserializationContext ctxt,JsonParser p,Object valueToUpdate) throws IOException {
  Object result;
  JsonToken t=_initForReading(ctxt,p);
  if (t == JsonToken.VALUE_NULL) {
    if (valueToUpdate == null) {
      result=_findRootDeserializer(ctxt).getNullValue(ctxt);
    }
 else {
      result=valueToUpdate;
    }
  }
 else   if (t == JsonToken.END_ARRAY || t == JsonToken.END_OBJECT) {
    result=valueToUpdate;
  }
 else {
    JsonDeserializer<Object> deser=_findRootDeserializer(ctxt);
    if (_unwrapRoot) {
      result=_unwrapAndDeserialize(p,ctxt,_valueType,deser);
    }
 else {
      if (valueToUpdate == null) {
        result=deser.deserialize(p,ctxt);
      }
 else {
        result=deser.deserialize(p,ctxt,valueToUpdate);
      }
    }
  }
  p.clearCurrentToken();
  if (_config.isEnabled(DeserializationFeature.FAIL_ON_TRAILING_TOKENS)) {
    _verifyNoTrailingTokens(p,ctxt,_valueType);
  }
  return result;
}
