@Override protected Query newWildcardQuery(final Term t){
  if (t.field() == null) {
    return super.newWildcardQuery(t);
  }
  Class<?> dataType=storeRetriever.get(t.field()).getDataType();
  if (Number.class.isAssignableFrom(dataType)) {
    try {
      return buildNumericQuery(t.field(),t.text(),dataType);
    }
 catch (    NumberFormatException e) {
      printNumberFormatException(t.field(),dataType,e);
    }
  }
  return super.newWildcardQuery(t);
}
