@Override public boolean serialize(final JsonContext jsonContext,final double[] array){
  if (array.length == 0 && jsonContext.isExcludeEmpty()) {
    return true;
  }
  jsonContext.writeOpenArray();
  for (int i=0; i < array.length; i++) {
    if (i > 0) {
      jsonContext.writeComma();
    }
    jsonContext.write(Double.toString(array[i]));
  }
  jsonContext.writeCloseArray();
  return true;
}
