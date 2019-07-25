/** 
 * Calculate boolean-specific statistics by accommodating the value and frequency/count
 */
@Override public void accomodate(Object columnValue,Long columnCount){
  accomodateCommon(columnValue,columnCount);
  if (columnValue != null) {
    columnBooleanValue=Boolean.valueOf(String.valueOf(columnValue));
    if (columnBooleanValue == Boolean.TRUE) {
      trueCount+=columnCount;
    }
 else {
      falseCount+=columnCount;
    }
  }
}
