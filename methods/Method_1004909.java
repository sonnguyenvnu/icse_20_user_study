@Override public Element _apply(final String json){
  try {
    return JSONSerialiser.deserialise(json,Element.class);
  }
 catch (  final SerialisationException ex) {
    throw new GafferRuntimeException("Unable to process JSON string: " + json + ". Message: " + ex.getMessage());
  }
}
