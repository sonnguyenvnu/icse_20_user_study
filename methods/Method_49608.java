@Override protected Query newRangeQuery(final String field,final String start,final String end,final boolean startInclusive,final boolean endInclusive){
  Class<?> dataType=storeRetriever.get(field).getDataType();
  if (Number.class.isAssignableFrom(dataType)) {
    try {
      return getNumericRangeQuery(field,dataType,start,end,startInclusive,endInclusive);
    }
 catch (    NumberFormatException e) {
      printNumberFormatException(field,dataType,e);
    }
  }
  return super.newRangeQuery(field,start,end,startInclusive,endInclusive);
}
