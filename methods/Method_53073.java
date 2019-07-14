/** 
 * Encodes  {@code value}.
 * @param value a {@link JSONObject},  {@link JSONArray}, String, Boolean, Integer, Long, Double or null. May not be  {@link Double#isNaN() NaNs}or  {@link Double#isInfinite() infinities}.
 * @return this stringer.
 * @throws JSONException On internal errors. Shouldn't happen.
 */
public JSONStringer value(Object value) throws JSONException {
  if (stack.isEmpty()) {
    throw new JSONException("Nesting problem");
  }
  if (value instanceof JSONArray) {
    ((JSONArray)value).writeTo(this);
    return this;
  }
 else   if (value instanceof JSONObject) {
    ((JSONObject)value).writeTo(this);
    return this;
  }
  beforeValue();
  if (value == null || value instanceof Boolean || value == JSONObject.NULL) {
    out.append(value);
  }
 else   if (value instanceof Number) {
    out.append(JSONObject.numberToString((Number)value));
  }
 else {
    string(value.toString());
  }
  return this;
}
