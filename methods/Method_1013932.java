@Override public void serialize(Set<T> dataObjects,OutputStreamWriter writer) throws Exception {
  gson.toJson(dataObjects,writer);
}
