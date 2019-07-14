@Override public void serializeValue(final JsonContext jsonContext,final Iterable iterable){
  jsonContext.writeOpenArray();
  int count=0;
  for (  Object element : iterable) {
    if (count > 0) {
      jsonContext.writeComma();
    }
    if (jsonContext.serialize(element)) {
      count++;
    }
  }
  jsonContext.writeCloseArray();
}
