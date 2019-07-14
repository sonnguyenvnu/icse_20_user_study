/** 
 * Make a JSON text of an Object value. If the object has an value.toJSONString() method, then that method will be used to produce the JSON text. The method is required to produce a strictly conforming text. If the object does not contain a toJSONString method (which is the most common case), then a text will be produced by other means. If the value is an array or Collection, then a JSONArray will be made from it and its toJSONString method will be called. If the value is a MAP, then a JSONObject will be made from it and its toJSONString method will be called. Otherwise, the value's toString method will be called, and the result will be quoted. <p> Warning: This method assumes that the data structure is acyclical.
 * @param value The value to be serialized.
 * @return a printable, displayable, transmittable representation of theobject, beginning with <code>{</code>&nbsp;<small>(left brace)</small> and ending with <code>}</code>&nbsp;<small>(right brace)</small>.
 * @throws JSONException If the value is or contains an invalid number.
 */
public static String valueToString(Object value) throws JSONException {
  if (value == null || value.equals(null)) {
    return "null";
  }
  if (value instanceof JSONString) {
    String object;
    try {
      object=((JSONString)value).toJSONString();
    }
 catch (    Exception e) {
      throw new JSONException(e);
    }
    if (object != null) {
      return object;
    }
    throw new JSONException("Bad value from toJSONString: " + object);
  }
  if (value instanceof Number) {
    final String numberAsString=JSONObject.numberToString((Number)value);
    if (JSONObject.NUMBER_PATTERN.matcher(numberAsString).matches()) {
      return numberAsString;
    }
    return JSONObject.quote(numberAsString);
  }
  if (value instanceof Boolean || value instanceof JSONObject || value instanceof JSONArray) {
    return value.toString();
  }
  if (value instanceof Map) {
    Map<?,?> map=(Map<?,?>)value;
    return new JSONObject(map).toString();
  }
  if (value instanceof Collection) {
    Collection<?> coll=(Collection<?>)value;
    return new JSONArray(coll).toString();
  }
  if (value.getClass().isArray()) {
    return new JSONArray(value).toString();
  }
  if (value instanceof Enum<?>) {
    return JSONObject.quote(((Enum<?>)value).name());
  }
  return JSONObject.quote(value.toString());
}
