String checkName(String name) throws JSONException {
  if (name == null) {
    throw new JSONException("Names must be non-null");
  }
  return name;
}
