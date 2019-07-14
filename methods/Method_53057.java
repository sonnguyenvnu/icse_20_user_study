public static JSONException typeMismatch(Object actual,String requiredType) throws JSONException {
  if (actual == null) {
    throw new JSONException("Value is null.");
  }
 else {
    throw new JSONException("Value " + actual + " of type " + actual.getClass().getName() + " cannot be converted to " + requiredType);
  }
}
