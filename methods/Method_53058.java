void writeTo(JSONStringer stringer) throws JSONException {
  stringer.array();
  for (  Object value : values) {
    stringer.value(value);
  }
  stringer.endArray();
}
