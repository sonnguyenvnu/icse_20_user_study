@Override public boolean serialize(final JsonContext jsonContext,final JsonArray jsonArray){
  final int length=jsonArray.size();
  if (length == 0 && jsonContext.isExcludeEmpty()) {
    return true;
  }
  jsonContext.writeOpenArray();
  int count=0;
  for (int i=0; i < length; i++) {
    if (count > 0) {
      jsonContext.writeComma();
    }
    if (jsonContext.serialize(jsonArray.getValue(i))) {
      count++;
    }
  }
  jsonContext.writeCloseArray();
  return true;
}
