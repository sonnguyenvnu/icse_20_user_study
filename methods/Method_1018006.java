/** 
 * Calculate bigdecimal-specific statistics by accommodating the value and frequency/count
 */
@Override public void accomodate(Object columnValue,Long columnCount){
  accomodateCommon(columnValue,columnCount);
  if (columnValue != null) {
    columnBigDecimalValue=new BigDecimal(String.valueOf(columnValue));
    columnBigDecimalCount=new BigDecimal(columnCount);
    if (max.compareTo(columnBigDecimalValue) < 0) {
      max=columnBigDecimalValue;
    }
    if (min.compareTo(columnBigDecimalValue) > 0) {
      min=columnBigDecimalValue;
    }
    sum=sum.add(columnBigDecimalValue.multiply(columnBigDecimalCount));
  }
}
