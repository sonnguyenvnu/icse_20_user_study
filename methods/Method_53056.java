public static JSONException typeMismatch(Object indexOrName,Object actual,String requiredType) throws JSONException {
  if (actual == null) {
    throw new JSONException("Value at " + indexOrName + " is null.");
  }
 else {
    throw new JSONException("Value " + actual + " at " + indexOrName + " of type " + actual.getClass().getName() + " cannot be converted to " + requiredType);
  }
}
